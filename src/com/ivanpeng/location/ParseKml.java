package com.ivanpeng.location;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

public class ParseKml extends Activity {

	final static String TAG = "ParseKmlActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_kml);
		// This will read and write the kml file.
		String filename = "history-11-17-2013.kml";
		//readFromFile();
		Kml kml = Kml.unmarshal(new File(filename));
		Placemark placemark = (Placemark) kml.getFeature();
		Point point = (Point) placemark.getGeometry();
		List<Coordinate> coordinates = point.getCoordinates();
		String s = "";
		for (Coordinate coordinate: coordinates)	{
			s = "Coordinate:\nLatitude: " + coordinate.getLatitude() +", Longitude: "+ coordinate.getLongitude();
			Log.v(TAG, s);
		}
		TextView tv = (TextView) findViewById(R.id.kml_coordinates);
		tv.setText(s);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parse_kml, menu);
		return true;
	}

	private String readFromFile(String file) {

	    String ret = "";

	    try {
	        InputStream inputStream = openFileInput(file);

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
	
}
