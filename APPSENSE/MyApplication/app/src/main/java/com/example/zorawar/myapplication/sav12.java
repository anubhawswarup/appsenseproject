package com.example.zorawar.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sav12 extends AppCompatActivity {

    //public static int value_time=0;
    Button but;
    EditText time_limit;
    TextView text;
   // LinearLayout layout = new LinearLayout(this);
    //AlertDialog ad =  new AlertDialog.Builder(this).create();
    //TextView tv1 = new TextView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sav12);

    }

    public void onClick(View v) {
        but = (Button) findViewById(R.id.but_sav1);
        time_limit = (EditText) findViewById(R.id.edit_text1);
        if(time_limit.getText().length()==0)
        {
            time_limit.setError("Please enter some number");
        }
        else{
        text = (TextView) findViewById(R.id.disp_text);
        text.setText(time_limit.getText());
        AlertDialog box= new AlertDialog.Builder(this).create();
        box.setTitle("Confirmation!!!");
        box.setMessage("Time Limit set to: " + time_limit.getText().toString());
        box.setButton(DialogInterface.BUTTON_NEUTRAL,"OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Time Accepted", Toast.LENGTH_SHORT).show();
            }
        });
        //box.setIcon(R.drawable.);

        //  text.setText(value_time);

    box.show();
    }}

                //                       String time = time_limit.getText().toString();
                //                     text.setText(time);
                //  layout.findViewById(R.id.alertTitle);
                // setContentView(layout);
                                     //  tv1.setText("Current time set:" + time);
                                       //ad.setView(tv1);
                                      // ad.show();


    }