package com.example.zorawar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class sav11 extends AppCompatActivity
{

    private static Button but_sav2;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sav11);
        onButtonClickListener();
        display_time();
    }

    public void display_time(){
    time=(TextView) findViewById(R.id.textView3);
    long curtime=System.currentTimeMillis();
        time.setText(String.valueOf(curtime));
    }

    public void onButtonClickListener() {

        but_sav2 = (Button) findViewById(R.id.but_sav2);
        but_sav2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent("com.example.zorawar.myapplication.sav12");
                        startActivity(i);
                    }
                }
        );

        ToggleButton tbtn_sav = (ToggleButton) findViewById(R.id.tbtn_sav);
        tbtn_sav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                  //  Toast.makeText(getApplicationContext(), "Service is Off", Toast.LENGTH_SHORT).show();
                    stop(buttonView);


                } else {
                 //   Toast.makeText(getApplicationContext(), "Service is On", Toast.LENGTH_SHORT).show();
                    start(buttonView);

                }
            }
        });
    }
            private void stop(CompoundButton buttonView)
            {
                Intent i=new Intent(getBaseContext(),Service_sav.class);
                Toast.makeText(getApplicationContext(), "Service is Off and stopped", Toast.LENGTH_LONG).show();
                stopService(i);
            }

            private void start(CompoundButton buttonView)
            {
                Intent i=new Intent(getBaseContext(),Service_sav.class);
                Toast.makeText(getApplicationContext(), "Service is On and started", Toast.LENGTH_LONG).show();
                startService(i);


            }

}
