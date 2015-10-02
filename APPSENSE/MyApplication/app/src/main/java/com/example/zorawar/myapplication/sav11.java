package com.example.zorawar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class sav11 extends AppCompatActivity {

    private static Button but_sav2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sav11);onButtonClickListener();
    }

    public void onButtonClickListener(){

        but_sav2=(Button)findViewById(R.id.but_sav2);
        but_sav2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent("com.example.zorawar.myapplication.sav12");
                        startActivity(i);
                    }
                }
        );

    }
}
