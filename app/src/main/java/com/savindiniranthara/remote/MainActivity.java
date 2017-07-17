package com.savindiniranthara.remote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public static int REQUEST_ENABLE_BT = 1;
    public static String EXTRA_ADDRESS = "btaddress";

    private BluetoothAdapter mBluetoothAdapter;
    ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceList = (ListView)findViewById(R.id.deviceList);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            //device does not support bluetooth
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Your device does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){
                            System.exit(0);
                        }
                    })
                    .show();
        }

        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPairedDevicesList();
            }
        });
    }

    public void showPairedDevicesList(){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        if(pairedDevices.size() > 0){
            for(BluetoothDevice bt: pairedDevices){
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        }else {
            Toast.makeText(getApplicationContext(), "No Paired Devices Found",Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener(myListClickListener);
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);
            Intent i = new Intent(MainActivity.this, TVRemoteActivity.class);
            i.putExtra(EXTRA_ADDRESS, address);
            startActivity(i);
        }
    };

    //Create a broadcast receiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                //A bluetooth device has been found
                //Get its info from the intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress();
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    //Connect as client
    private class ConnectThread extends Thread{
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device){
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                //get a Bluetooth socket to connect with the given bluetooth device
                tmp = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
            }catch (IOException e){
                //creating socket failed
            }

            mmSocket = tmp;
        }

        public void run(){
            //Cancel discovery because it otherwise slows down the connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                //Connect to the remote device through the socket
                mmSocket.connect();
            }catch (IOException connectException){
                //Unable to connect, close the socket and return
                try{
                    mmSocket.close();
                }catch (IOException closeException){
                    //could not close the socket
                }
                return;
            }

            //connection successfull
            //Perform the work associated with the connection in a separate thread
            //TODO: manageMyConnectedSocket(mmSocket);
        }

        public void cancel(){
            //closes the client socket and causes the thread to finish
            try{
                mmSocket.close();
            }catch (IOException e){
                //could not close the socket
            }
        }
    }
}
