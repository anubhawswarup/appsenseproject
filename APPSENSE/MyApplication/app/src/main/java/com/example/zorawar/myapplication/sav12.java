package com.example.zorawar.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sav12 extends AppCompatActivity
{

    //public static int value_time=0;
    Button but;
    EditText time_limit;
    TextView text;
    Float temp;
    public static SharedPreferences limitshared;

    // LinearLayout layout = new LinearLayout(this);
    //AlertDialog ad =  new AlertDialog.Builder(this).create();
    //TextView tv1 = new TextView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sav12);
        limitshared = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = limitshared.edit();
        temp = limitshared.getFloat("time_limit", 0.0f);
        text = (TextView) findViewById(R.id.disp_text);
        text.setText(String.valueOf(temp));


    }

    public void onClick(View v) {
        but = (Button) findViewById(R.id.but_sav1);
        time_limit = (EditText) findViewById(R.id.edit_text1);




        if (time_limit.getText().length() == 0) {
            time_limit.setError("Please enter some number");
        } else {
            limitshared = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = limitshared.edit();
           // editor.putFloat("time_limit", 0.0f);
            editor.putFloat("time_limit", Float.parseFloat(time_limit.getText().toString()));
            editor.commit();
            text = (TextView) findViewById(R.id.disp_text);
            temp = limitshared.getFloat("time_limit", 0.0f);
            text.setText(String.valueOf(temp));
            //calculation();
            AlertDialog box = new AlertDialog.Builder(this).create();
            box.setTitle("Confirmation!!!");
            box.setMessage("Time Limit set to: " + time_limit.getText().toString());
            box.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    );
                    Toast.makeText(getApplicationContext(), "Time Accepted", Toast.LENGTH_SHORT).show();
                }
            });
            //box.setIcon(R.drawable.);

            //  text.setText(value_time);

            box.show();
            calculation();
        }

    }

    public void calculation()
    {

        Float p = limitshared.getFloat("time_limit", 0.0f);
        //Toast.makeText(getBaseContext(),"get this p"+ String.valueOf(p),Toast.LENGTH_LONG).show();
        long convertedtime= (long) (p * 3600000);
        SharedPreferences.Editor editor=limitshared.edit();
        //editor.putLong("convertedtime", 0l);
        editor.putLong("convertedtime", convertedtime);
        editor.commit();

        Long p1 = limitshared.getLong("convertedtime",0l);
        Toast.makeText(getBaseContext(),"get this p"+ String.valueOf(p1),Toast.LENGTH_LONG).show();
    }
}
                //                       String time = time_limit.getText().toString();
                //                     text.setText(time);
                //  layout.findViewById(R.id.alertTitle);
                // setContentView(layout);
                                     //  tv1.setText("Current time set:" + time);
                                       //ad.setView(tv1);
                                      // ad.show();
