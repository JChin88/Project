package edu.gatech.cs2340.cs2340project.data;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;


public class LocationDataRepository implements LocationRepository {

    private FirebaseFirestore db;
    private Interactor interactor;

    public LocationDataRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getLocation(String key) {

    }

    @Override
    public void addLocations(InputStream locationDataFile) {
        Collection<Location> locationCollection = new ArrayList<>();
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
                db.collection("Donation Locations").document().set(tempLocation, SetOptions.merge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.collection("Donation Locations").add(locationCollection);
    }

    @Override
    public void getLocationList() {
        final List<Location> listLocation = new ArrayList<>();
        CollectionReference locationListRef = db.collection("Donation Locations");
        locationListRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                        Location location = documentSnapshot.toObject(Location.class);
                        listLocation.add(location);
                    }
                    interactor.onNext(listLocation);
                } else {
                    interactor.onError("Retrived list of locations failed");
                }
            }
        });
    }



}
