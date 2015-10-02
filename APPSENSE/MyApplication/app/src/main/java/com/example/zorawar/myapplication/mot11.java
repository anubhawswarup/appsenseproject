package com.example.zorawar.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mot11 extends Activity{

    private static Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mot11);onButtonClickListener();
    }

    public void onButtonClickListener(){

        but=(Button)findViewById(R.id.button7);
        but.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent("com.example.zorawar.myapplication.mot12");
                        startActivity(i);
                    }
                }
        );

    }


}
