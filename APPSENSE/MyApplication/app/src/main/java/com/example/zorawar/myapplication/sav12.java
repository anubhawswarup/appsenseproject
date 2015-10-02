package com.example.zorawar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class sav12 extends AppCompatActivity {

    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sav12);
    }


    public void onButtonClickListener(){

        but=(Button)findViewById(R.id.but_sav1);
        but.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent("com.example.zorawar.myapplication.sav12");
                        startActivity(i);
                    }
                }
        );

    }
}