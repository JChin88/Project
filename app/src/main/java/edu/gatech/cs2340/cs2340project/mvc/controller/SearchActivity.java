package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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

public class SearchActivity extends AppCompatActivity {
    private RadioGroup search_params;
    private RadioButton category;
    private RadioButton item;
    private EditText search;
    private Spinner locations;
    private Button startSearch;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donationItemRef = db.collection("Donation Items");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        readLocationData();

        search_params = findViewById(R.id.search_params);
        category = findViewById(R.id.category);
        item = findViewById(R.id.item);
        search = findViewById(R.id.search);
        locations = findViewById(R.id.locations);
        startSearch = findViewById(R.id.start_search);

        List<String> locationNameList = new ArrayList<String>();
        locationNameList.add("All Locations");
        for (Location location: LocationData.getLocationList()) {
            locationNameList.add(location.getName());
        }

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locationNameList);
        locations.setAdapter(locationAdapter);
        locations.setSelection(0);

    }

    public void onSearchPress(View view) {
        String searchWords = search.getText().toString().trim();
        int checkedButtonId = search_params.getCheckedRadioButtonId();
        String param = (checkedButtonId == R.id.category) ? "donationItemCategory" : "donationItemName";
        String location = locations.getSelectedItem().toString();
        Query query;
        if (location.equals("All Locations")) {
            query = donationItemRef;
        } else {
            query = donationItemRef.whereEqualTo("donationItemLocation", location);
        }
        query = donationItemRef.whereEqualTo(param, searchWords);

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
