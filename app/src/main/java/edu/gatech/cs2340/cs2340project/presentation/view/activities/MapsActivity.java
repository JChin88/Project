package edu.gatech.cs2340.cs2340project.presentation.view.activities;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import edu.gatech.cs2340.cs2340project.R;

/**
 * @author Jonathan Chin
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a markers and move the camera
        LatLng AFDStation4 = new LatLng(33.75416, -84.37742);
        LatLng boysAndGirls = new LatLng(33.73182, -84.43971);
        LatLng pathwayUpper = new LatLng(33.70866, -84.41853);
        LatLng pavilionOfHope = new LatLng(33.80129, -84.25537);
        LatLng dAndD = new LatLng(33.71747, -84.2521);
        LatLng keepNorthFultonBeautiful = new LatLng(33.96921, -84.3688);
        googleMap.addMarker(new MarkerOptions().position(AFDStation4)
                .title("AFD Station 4")
                .snippet("(404) 555 - 3456"));
        googleMap.addMarker(new MarkerOptions().position(boysAndGirls)
                .title("Boys and Girls Club")
                .snippet("(404) 555 - 1234"));
        googleMap.addMarker(new MarkerOptions().position(pathwayUpper).title("Pathway Upper")
                .snippet("(404) 555 - 5432"));
        googleMap.addMarker(new MarkerOptions().position(pavilionOfHope).title("Pavilion of Hope")
                .snippet("(404) 555 - 8765"));
        googleMap.addMarker(new MarkerOptions().position(dAndD).title("D&D")
                .snippet("(404) 555 - 9876"));
        googleMap.addMarker(new MarkerOptions().position(keepNorthFultonBeautiful)
                .title("Keep North Fulton Beautiful").snippet("(770) 555 - 7321"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(AFDStation4));
    }
}
