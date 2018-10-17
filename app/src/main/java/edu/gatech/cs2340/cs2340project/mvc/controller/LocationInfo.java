package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.mvc.model.Location;
import edu.gatech.cs2340.cs2340project.mvc.model.LocationData;

public class LocationInfo extends AppCompatActivity {

    //Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
    // 0    1   2       3           4               5   6   7   8       9   10

    private TextView locationName;
    private TextView locationLatitude;
    private TextView locationLongtitude;
    private TextView locationAddress;
    private TextView locationType;
    private TextView locationPhone;
    private TextView locationWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        locationName = findViewById(R.id._locationName);
        locationLatitude = findViewById(R.id._locationLatitude);
        locationLongtitude = findViewById(R.id._locationLongtitude);
        locationAddress = findViewById(R.id._locationAddress);
        locationType = findViewById(R.id._locationType);
        locationPhone = findViewById(R.id._locationPhone);
        locationWebsite = findViewById(R.id._locationWebsite);

        Intent tempIntent = getIntent();
        Integer location = Integer.parseInt(tempIntent.getStringExtra("key"));
        setTextWithKey(location);

    }

    public void setTextWithKey(Integer key) {
        Location tempLocation = LocationData.getLocation(key);
        locationName.setText("Location Name: \t" + tempLocation.getName());
        locationLatitude.setText("Location Latitude: \t" + Double.toString(tempLocation.getLatitude()));
        locationLongtitude.setText("Location Longtitude: \t" + Double.toString(tempLocation.getLongtitude()));
        locationAddress.setText("Location Address: \t" + tempLocation.getAddress());
        locationType.setText("Location Type: \t" + tempLocation.getType());
        locationPhone.setText("Location Phone Number: \t" + tempLocation.getPhoneNumber());
        locationWebsite.setText("Location Website: \t" + tempLocation.getWebsite());
    }
}
