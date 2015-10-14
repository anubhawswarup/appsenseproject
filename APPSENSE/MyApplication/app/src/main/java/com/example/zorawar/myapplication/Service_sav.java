package com.example.zorawar.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import static java.lang.Math.abs;

public class Service_sav extends Service implements SensorEventListener
{


    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    float[] inclineGravity = new float[3];
    float[] mGravity;
    float[] mGeomagnetic;
    float pitch;
    float roll;
    private boolean sersorrunning;
    SharedPreferences sensorvalues;
    private static SensorManager mySensorManager;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        SharedPreferences limitshared=getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        sensorvalues=getSharedPreferences("Mydata",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sensorvalues.edit();
        editor.putLong("lastupdatingtime",System.currentTimeMillis());
        editor.commit();

        Long convertedtime=limitshared.getLong("convertedtime", 0l);
        Toast.makeText(this,"inside onStart function",Toast.LENGTH_LONG).show();
        Toast.makeText(this,(String.valueOf(convertedtime))+": millis",Toast.LENGTH_LONG).show();



        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mySensors = mySensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        if (mySensors.size() > 0)
        {
            mySensorManager.registerListener(mySensorEventListener, mySensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
            sersorrunning = true;
            Toast.makeText(this, "Start ORIENTATION Sensor", Toast.LENGTH_LONG).show();
        } else
        {
            Toast.makeText(this, "No ORIENTATION Sensor", Toast.LENGTH_LONG).show();
            sersorrunning = false;
        }

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();

        if (sersorrunning)
        {
            mySensorManager.unregisterListener(mySensorEventListener);
            Toast.makeText(Service_sav.this, "unregisterListener", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(this,"destroyed",Toast.LENGTH_LONG).show();
        //mSensorManager.unregisterListener(this);
        //super.onDestroy();
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Long time=System.currentTimeMillis();
        Long lastupdate=sensorvalues.getLong("lastupdatingtime", 0l);
        Long convertedtime=sensorvalues.getLong("convertedtime",0l);

        if(phonemoved(event))
        {
            if( (time-lastupdate) < convertedtime )
            {
                Toast.makeText(getBaseContext(), "MOVED,less_time_limit", Toast.LENGTH_SHORT).show();
            }
           // textviewAzimuth.setText("Azimuth: " + String.valueOf(event.values[0]));
            //textviewPitch.setText("Pitch: " + String.valueOf(event.values[1]));
            //textviewRoll.setText("Roll: " + String.valueOf(event.values[2]));

        }
        else
        {
            if( (time-lastupdate) > convertedtime )
            {
                Toast.makeText(getBaseContext(),"No Movement,time_more_limit",Toast.LENGTH_SHORT).show();
            }
            if((time-lastupdate) < convertedtime )
            {
                Toast.makeText(getBaseContext(),"time:"+String.valueOf(time)+" lastupdate:"+ String.valueOf(lastupdate),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(),"No Movement,time_less_limit",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean phonemoved(SensorEvent event)
    {
        sensorvalues=getSharedPreferences("Mydata",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sensorvalues.edit();
        Float lastX=sensorvalues.getFloat("Azimuth",0.0f);
        Float lastY=sensorvalues.getFloat("Pitch",0.0f);
        Float lastZ=sensorvalues.getFloat("Roll",0.0f);
        if ( (abs(event.values[0] - lastX) > 5) || ((abs(event.values[1] - lastY) > 5)) || ((abs(event.values[2] - lastZ) > 5)) )
        {

            editor.putLong("lastupdatingtime",System.currentTimeMillis());
            editor.putFloat("Azimuth", event.values[0]);
            editor.putFloat("Pitch", event.values[1]);
            editor.putFloat("Roll", event.values[2]);
            editor.commit();
            Toast.makeText(getBaseContext(),"MOVED",Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    private SensorEventListener mySensorEventListener = new SensorEventListener()
    {

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            Long time=System.currentTimeMillis();
            Long lastupdate=sensorvalues.getLong("lastupdatingtime", 0l);
            Long convertedtime=sensorvalues.getLong("convertedtime",0l);

            if(phonemoved(event))
            {
                if( (time-lastupdate) < convertedtime )
                {
                    Toast.makeText(getBaseContext(), "MOVED within Limit", Toast.LENGTH_SHORT).show();
                }
               // textviewAzimuth.setText("Azimuth: " + String.valueOf(event.values[0]));
               // textviewPitch.setText("Pitch: " + String.valueOf(event.values[1]));
                //textviewRoll.setText("Roll: " + String.valueOf(event.values[2]));

            }
            else
            {
                if( (time-lastupdate) > convertedtime )
                {
                    Toast.makeText(getBaseContext(),"Switch OFF DATA",Toast.LENGTH_LONG).show();
                }
                if((time-lastupdate) < convertedtime )
                {
                    Toast.makeText(getBaseContext(),"time:"+String.valueOf(time)+" last:"+ String.valueOf(lastupdate),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getBaseContext(),"No Movement,time_less_limit",Toast.LENGTH_SHORT).show();
                }
            }
            // TODO Auto-generated method stub





            //  textviewAzimuth.setText("Azimuth: " + String.valueOf(event.values[0]));
            // textviewPitch.setText("Pitch: " + String.valueOf(event.values[1]));
            //textviewRoll.setText("Roll: " + String.valueOf(event.values[2]));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
            // TODO Auto-generated method stub

        }
    };
}
