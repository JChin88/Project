package edu.gatech.cs2340.cs2340project.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.model.Location;
import edu.gatech.cs2340.cs2340project.model.LocationData;

public class LocationInfo extends AppCompatActivity {

    TextView locationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);
        readLocationData();
        locationInfo = findViewById(R.id.location_Data1);
        locationInfo.setText(LocationData.getLocation(1).toString());
    }

    public void readLocationData() {
        InputStream locationDataFile = getResources().openRawResource(R.raw.location_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(locationDataFile, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                Location tempLocation = new Location();

                //Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
                // 0    1   2       3           4               5   6   7   8       9   10
                tempLocation.setName(part[1]);
                tempLocation.setLatitude(Double.parseDouble(part[2]));
                tempLocation.setLongtitude(Double.parseDouble(part[3]));
                tempLocation.setAddress(part[4] + ", " + part[5] +  ", " + part[6] + ", " + part[7]);
                tempLocation.setType(part[8]);
//                tempLocation.setPhoneNumber(convertStringPhoneNumber(part[9]));
                tempLocation.setPhoneNumber(part[9]);
                tempLocation.setWebsite(part[10]);
                LocationData.addLocation(Integer.parseInt(part[0]),tempLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Integer convertStringPhoneNumber(String phoneNumber) {
//        String tempPN = "";
//        for (int i = 0; i < phoneNumber.length() - 1; i++) {
//            if (Character.isDigit(phoneNumber.charAt(i))) {
//                tempPN += phoneNumber.charAt(i) + "";
//            }
//        }
//        return Integer.parseInt(tempPN);
//    }
}

