package com.example.zorawar.myapplication;

        import android.app.Activity;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.os.Vibrator;
        import android.widget.TextView;
        import android.widget.Toast;

public class test extends Activity implements SensorEventListener {
        public float lastX, lastY, lastZ;
        private SensorManager sensorManager;
        private Sensor accelerometer;
        private float deltaXMax = 0;
        private float  deltaYMax = 0;
        private float deltaZMax = 0;

        private float deltaX = 0;
        private float deltaY = 0;
        private float deltaZ = 0;

        private float vibrateThreshold = 0;
            long lastUpdate = System.currentTimeMillis();

        private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;
        public Vibrator v;

         @Override
     protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        initializeViews();
       // startService(new Intent(getBaseContext(), test.class));

        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        { // success! we have an accelerometer
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        vibrateThreshold=2;
        }
        else
        {
// fai! we don't have an accelerometer!
        }
//initialize vibration

        }
            public void initializeViews() {

                currentX=(TextView) findViewById(R.id.currentX);
                currentY =(TextView) findViewById(R.id.currentY);
                currentZ =(TextView) findViewById(R.id.currentZ);
                maxX=(TextView) findViewById(R.id.maxX);
                maxY=(TextView) findViewById(R.id.maxY);
                maxZ=(TextView) findViewById(R.id.maxZ);
//                lastX=Float.parseFloat(currentX.getText().toString());
  //              lastY=Float.parseFloat(currentY.getText().toString());
    //            lastZ=Float.parseFloat(currentZ.getText().toString());
            }





            protected void onResume()
            {

                super.onResume();

                sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            }



          //  public void startService(View v) {
            //    startService(new Intent(getBaseContext(), test.class));
           // }

            //onPause() unregister the accelerometer for stop listening the events
         protected void onPause()
         {
         super.onPause();
         sensorManager.unregisterListener(this);
         }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        @Override
        public void onSensorChanged(SensorEvent event) {

// In onSensorChanged:
            //long curTime = System.currentTimeMillis();


            // clean current values
            displayCleanValues();
            // display the current x,y,z accelerometer values
            displayCurrentValues(event);
            // display the max x,y,z accelerometer values
            displayMaxValues();

            // get the change of the x,y,z values of the accelerometer
            deltaX = Math.abs(lastX - event.values[0]);
            deltaY = Math.abs(lastY - event.values[1]);
            deltaZ = Math.abs(lastZ - event.values[2]);

            // if the change is below 2, it is just plain noise
            if (deltaX < vibrateThreshold)
                deltaX = 0;
            if (deltaY < vibrateThreshold)
                deltaY = 0;
            if (deltaZ < vibrateThreshold)
                deltaZ = 0;
            lastX = event.values[0];
            lastY = event.values[1];
            lastZ = event.values[2];
            vibrate();
        }
        public void vibrate()
        {
            long curTime=System.currentTimeMillis();
            v=(Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        if(( deltaZ > vibrateThreshold ) || ( deltaY > vibrateThreshold ) || ( deltaZ > (vibrateThreshold-1) ))
        {
            if ((curTime - lastUpdate) > 2000){ // only reads data twice per second
                lastUpdate = curTime;
                Toast.makeText(getApplicationContext(),"time: "+lastUpdate,Toast.LENGTH_SHORT).show();
            }
        v.vibrate(50);
        }
        }

        public void displayCleanValues()
        {
            currentX.setText("0.0");
            currentY.setText("0.0");
            currentZ.setText("0.0");
        }
// display the current x,y,z accelerometer values
public void displayCurrentValues(SensorEvent event)
{
        currentX.setText(Float.toString(event.values[0]));
        currentY.setText(Float.toString(event.values[1]));
        currentZ.setText(Float.toString(event.values[2]));
}

// display the max x,y,z accelerometer values
public void displayMaxValues()
{
        if(deltaX > deltaXMax)
        {
            deltaXMax = deltaX;
            maxX.setText(Float.toString(deltaXMax));
        }

        if(deltaY > deltaYMax)
        {
            deltaYMax = deltaY;
            maxY.setText(Float.toString(deltaYMax));
        }

        if(deltaZ > deltaZMax)
        {
            deltaZMax = deltaZ;
            maxZ.setText(Float.toString(deltaZMax));
        }
}
}