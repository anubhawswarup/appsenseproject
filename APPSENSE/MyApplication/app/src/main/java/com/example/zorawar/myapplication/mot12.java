package com.example.zorawar.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mot12 extends Activity{

    private static Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mot12);onButtonClickListener();
    }

    public void onButtonClickListener(){

        but=(Button)findViewById(R.id.button8);
        but.setOnClickListener(
                new  View.OnClickListener()
                {
                    public void onClick(View v){
                        Intent i=new Intent("com.example.zorawar.myapplication.mot13");
                        startActivity(i);
                    }
                }
        );

    }

}