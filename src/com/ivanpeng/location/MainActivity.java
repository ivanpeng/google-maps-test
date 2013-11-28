package com.ivanpeng.location;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	static final LatLng TORONTO = new LatLng(43.670852, -79.376435);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		addListenerToButton();
		//addListenerToKmlButton();
	}
	
	protected void addListenerToButton()	{
		Button button = (Button) findViewById(R.id.location_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (googleMap.isMyLocationEnabled() == false)
					googleMap.setMyLocationEnabled(true);
				else
					googleMap.setMyLocationEnabled(false);
			}
		});
	}
	
	public void addListenerToKmlButton()	{
		Button button = (Button) findViewById(R.id.location_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ParseKml.class);
				startActivity(intent);
			}
		});
	}

	private void initializeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			Marker toronto = googleMap.addMarker(new MarkerOptions().position(
					TORONTO).title("Toronto"));
			// Move the camera instantly to hamburg with a zoom of 15.
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TORONTO, 15));

			// Zoom in, animating the camera.
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		initializeMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
