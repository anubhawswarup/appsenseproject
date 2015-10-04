package com.example.accelerometerdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AccelerometerListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onAccelerationChanged(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShake(float force) {
		Toast.makeText(this, "Motion detected", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume Accelerometer Started", Toast.LENGTH_SHORT).show();
		
		if(AccelerometerManager.isSupported(this)) {
			AccelerometerManager.startListening(this);
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if(AccelerometerManager.isListening()) {
			AccelerometerManager.stopListening();
			Toast.makeText(this, "onStop Accelerometer Stoped", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(AccelerometerManager.isListening()) {
			AccelerometerManager.stopListening();
			Toast.makeText(this, "onDestroy Accelerometer Stoped", Toast.LENGTH_SHORT).show();
		}
	}
}
