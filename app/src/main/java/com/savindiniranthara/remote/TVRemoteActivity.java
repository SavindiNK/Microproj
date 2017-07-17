package com.savindiniranthara.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;


public class TVRemoteActivity extends AppCompatActivity {

    public static String EXTRA_ADDRESS = "btaddress";
    String address;
    ToggleButton modeButton;
    String mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        address = intent.getStringExtra(EXTRA_ADDRESS);

        setContentView(R.layout.activity_tvremote);
        modeButton = (ToggleButton)findViewById(R.id.toggleButton);
        modeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mode = "~L";
                }else {
                    mode = "~O";
                }
            }
        });
    }

    public void sendCode(String code){
        if(btSocket != null){
            try {
                String msg = mode + code + "#";
                btSocket.getOutputStream().write(msg.toString().getBytes());
            }catch (IOException e){
                Toast.makeText(getApplicationContext(),"An Error Occured!",Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getCode(){
        //get code from storage
    }

    public void power(View view){
        //send code for power
        String code = getCode();
        sendCode(code);
    }

    public void volumeUp(View view){
        //send code for vol up
        String code = getCode();
        sendCode(code);
    }

    public void volumeDown(View view){
        //send code for vol down
        String code = getCode();
        sendCode(code);
    }

    public void next(View view){
        //send code for next
        String code = getCode();
        sendCode(code);
    }

    public void previous(View view){
        //send code for previous
        String code = getCode();
        sendCode(code);
    }

    public void zero(View view){
        //send code for 0
        String code = getCode();
        sendCode(code);
    }

    public void one(View view){
        //send code for 1
        String code = getCode();
        sendCode(code);
    }

    public void two(View view){
        //send code for 2
        String code = getCode();
        sendCode(code);
    }

    public void three(View view){
        //send code for 3
        String code = getCode();
        sendCode(code);
    }

    public void four(View view){
        //send code for 4
        String code = getCode();
        sendCode(code);
    }

    public void five(View view){
        //send code for 5
        String code = getCode();
        sendCode(code);
    }

    public void six(View view){
        //send code for 6
        String code = getCode();
        sendCode(code);
    }

    public void seven(View view){
        //send code for 7
        String code = getCode();
        sendCode(code);
    }

    public void eight(View view){
        //send code for 8
        String code = getCode();
        sendCode(code);
    }

    public void nine(View view){
        //send code for 9
        String code = getCode();
        sendCode(code);
    }
}
