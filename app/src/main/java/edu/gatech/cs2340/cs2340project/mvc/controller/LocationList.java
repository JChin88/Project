package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.LocationData;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

public class LocationList extends AppCompatActivity {

    TextView locationInfo;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        db = FirebaseFirestore.getInstance();
        readLocationData();

        //
        List<String> locationNameList = new ArrayList<String>();
        for (Location location: LocationData.getLocationList()) {
            locationNameList.add(location.getName());
        }

//
//        for (Location location: LocationData.getLocationList()) {
//            db.collection("Donation Locations").add(location)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
////                            Toast.makeText(LocationList.this,
////                                    "Add a list of location success!!", Toast.LENGTH_LONG).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(LocationList.this,
//                            "Add a list of location failed!!", Toast.LENGTH_LONG).show();
//                }
//            });
//        }

        ListAdapter locationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, locationNameList);
        ListView locationListView = findViewById(R.id._locationList);
        locationListView.setAdapter(locationAdapter);

        locationListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int locationClickparent = position + 1 ;
                        Intent moveToLocationInfo = new Intent(LocationList.this, LocationInfo.class);
                        moveToLocationInfo.putExtra("key", locationClickparent + "");
                        LocationList.this.startActivity(moveToLocationInfo);
                    }
                }
        );

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
//              tempLocation.setPhoneNumber(convertStringPhoneNumber(part[9]));
                tempLocation.setPhoneNumber(part[9]);
                tempLocation.setWebsite(part[10]);
                LocationData.addLocation(Integer.parseInt(part[0]),tempLocation);
//                db.collection("Donation Locations").add(tempLocation)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
////                            Toast.makeText(LocationList.this,
////                                    "Add a list of location success!!", Toast.LENGTH_LONG).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(LocationList.this,
//                                "Add a list of location failed!!", Toast.LENGTH_LONG).show();
//                    }
//                });
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

