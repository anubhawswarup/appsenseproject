package com.example.zorawar.myapplication;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by anubhawswarup on 13/10/15.
 */
    public class test2 extends Service implements SensorEventListener
    {


        private SensorManager mSensorManager;
        private Sensor accelerometer;
        private Sensor magnetometer;
        float[] inclineGravity = new float[3];
        float[] mGravity;
        float[] mGeomagnetic;
        float pitch;
        float roll;


        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }


        @Override
        public int onStartCommand(Intent intent, int flags, int startId)
        {

            Toast.makeText(this, "inside onStart function", Toast.LENGTH_LONG).show();
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            initListeners();
            return super.onStartCommand(intent, flags, startId);
        }

        public void initListeners()
        {
            mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
        }

        @Override
        public void onDestroy()
        {
            Toast.makeText(this,"destroyed",Toast.LENGTH_LONG).show();
            mSensorManager.unregisterListener(this);
            super.onDestroy();
        }


        @Override
        public void onSensorChanged(SensorEvent event)
        {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                mGravity = event.values;
            }
            else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                mGeomagnetic = event.values;

                if (isTiltDownward())
                {
                    Toast.makeText(getBaseContext(),"Tilted DOWN",Toast.LENGTH_SHORT).show();
                    Log.d("test", "downwards");
                }
                else if (isTiltUpward())
                {
                    Toast.makeText(getBaseContext(),"Tilted UP",Toast.LENGTH_SHORT).show();
                    Log.d("test", "upwards");
                }
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        public boolean isTiltUpward()
        {
            if (mGravity != null && mGeomagnetic != null)
            {
                float R[] = new float[9];
                float I[] = new float[9];

                boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

                if (success)
                {
                    float orientation[] = new float[3];
                    SensorManager.getOrientation(R, orientation);

				/*
				* If the roll is positive, you're in reverse landscape (landscape right), and if the roll is negative you're in landscape (landscape left)
				*
				* Similarly, you can use the pitch to differentiate between portrait and reverse portrait.
				* If the pitch is positive, you're in reverse portrait, and if the pitch is negative you're in portrait.
				*
				* orientation -> azimuth, pitch and roll
				*
				*
				*/

                    pitch = orientation[1];
                    roll = orientation[2];

                    inclineGravity = mGravity.clone();

                    double norm_Of_g = Math.sqrt(inclineGravity[0] * inclineGravity[0] + inclineGravity[1] * inclineGravity[1] + inclineGravity[2] * inclineGravity[2]);

                    // Normalize the accelerometer vector
                    inclineGravity[0] = (float) (inclineGravity[0] / norm_Of_g);
                    inclineGravity[1] = (float) (inclineGravity[1] / norm_Of_g);
                    inclineGravity[2] = (float) (inclineGravity[2] / norm_Of_g);

                    //Checks if device is flat on ground or not
                    int inclination = (int) Math.round(Math.toDegrees(Math.acos(inclineGravity[2])));

				/*
				* Float obj1 = new Float("10.2");
				* Float obj2 = new Float("10.20");
				* int retval = obj1.compareTo(obj2);
				*
				* if(retval > 0) {
				* System.out.println("obj1 is greater than obj2");
				* }
				* else if(retval < 0) {
				* System.out.println("obj1 is less than obj2");
				* }
				* else {
				* System.out.println("obj1 is equal to obj2");
				* }
				*/
                    Float objPitch = new Float(pitch);
                    Float objZero = new Float(0.0);
                    Float objZeroPointTwo = new Float(0.2);
                    Float objZeroPointTwoNegative = new Float(-0.2);

                    int objPitchZeroResult = objPitch.compareTo(objZero);
                    int objPitchZeroPointTwoResult = objZeroPointTwo.compareTo(objPitch);
                    int objPitchZeroPointTwoNegativeResult = objPitch.compareTo(objZeroPointTwoNegative);

                    if (roll < 0 && ((objPitchZeroResult > 0 && objPitchZeroPointTwoResult > 0) || (objPitchZeroResult < 0 && objPitchZeroPointTwoNegativeResult > 0)) && (inclination > 30 && inclination < 40))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            return false;
        }

        public boolean isTiltDownward()
        {
            if (mGravity != null && mGeomagnetic != null)
            {
                float R[] = new float[9];
                float I[] = new float[9];

                boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

                if (success)
                {
                    float orientation[] = new float[3];
                    SensorManager.getOrientation(R, orientation);

                    pitch = orientation[1];
                    roll = orientation[2];

                    inclineGravity = mGravity.clone();

                    double norm_Of_g = Math.sqrt(inclineGravity[0] * inclineGravity[0] + inclineGravity[1] * inclineGravity[1] + inclineGravity[2] * inclineGravity[2]);

                    // Normalize the accelerometer vector
                    inclineGravity[0] = (float) (inclineGravity[0] / norm_Of_g);
                    inclineGravity[1] = (float) (inclineGravity[1] / norm_Of_g);
                    inclineGravity[2] = (float) (inclineGravity[2] / norm_Of_g);

                    //Checks if device is flat on groud or not
                    int inclination = (int) Math.round(Math.toDegrees(Math.acos(inclineGravity[2])));

                    Float objPitch = new Float(pitch);
                    Float objZero = new Float(0.0);
                    Float objZeroPointTwo = new Float(0.2);
                    Float objZeroPointTwoNegative = new Float(-0.2);

                    int objPitchZeroResult = objPitch.compareTo(objZero);
                    int objPitchZeroPointTwoResult = objZeroPointTwo.compareTo(objPitch);
                    int objPitchZeroPointTwoNegativeResult = objPitch.compareTo(objZeroPointTwoNegative);

                    if (roll < 0 && ((objPitchZeroResult > 0 && objPitchZeroPointTwoResult > 0) || (objPitchZeroResult < 0 && objPitchZeroPointTwoNegativeResult > 0)) && (inclination > 140 && inclination < 170))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            return false;
        }
    }
