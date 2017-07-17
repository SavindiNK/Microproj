package com.savindiniranthara.remote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


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

    public void power(View view){
        //send code for power

    }

    public void volumeUp(View view){
        //send code for vol up
    }

    public void volumeDown(View view){
        //send code for vol down
    }

    public void next(View view){
        //send code for next
    }

    public void previous(View view){
        //send code for previous
    }

    public void zero(View view){
        //send code for 0
    }

    public void one(View view){
        //send code for 1
    }

    public void two(View view){
        //send code for 2
    }

    public void three(View view){
        //send code for 3
    }

    public void four(View view){
        //send code for 4
    }

    public void five(View view){
        //send code for 5
    }

    public void six(View view){
        //send code for 6
    }

    public void seven(View view){
        //send code for 7
    }

    public void eight(View view){
        //send code for 8
    }

    public void nine(View view){
        //send code for 9
    }
}
