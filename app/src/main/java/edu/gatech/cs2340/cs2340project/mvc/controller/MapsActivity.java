package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gatech.cs2340.cs2340project.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
/**
 * Implement kth select.
 *
 * Use the provided random object to select your pivots.
 * For example if you need a pivot between a (inclusive)
 * and b (exclusive) where b > a, use the following code:
 *
 * int pivotIndex = r.nextInt(b - a) + a;
 *
 * It should be:
 *  in-place
 *
 * Have a worst case running time of:
 *  O(n^2)
 *
 * And a best case running time of:
 *  O(n)
 *
 * You may assume that the array doesn't contain any null elements.
 *
 * Make sure you code the algorithm as you have been taught it in class.
 * There are several versions of this algorithm and you may not get full
 * credit if you do not implement the one we have taught you!
 *
 * @throws IllegalArgumentException if the array or comparator or rand is
 * null or k is not in the range of 1 to arr.length
 * @param <T> data type to sort
 * @param k the index to retrieve data from + 1 (due to 0-indexing) if
 *          the array was sorted; the 'k' in "kth select"; e.g. if k ==
 *          1, return the smallest element in the array
 * @param arr the array that should be modified after the method
 * is finished executing as needed
 * @param comparator the Comparator used to compare the data in arr
 * @param rand the Random object used to select pivots
 * @return the kth smallest element
 */

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
        mMap = googleMap;

        // Add a markers and move the camera
        LatLng AFDStation4 = new LatLng(33.75416, -84.37742);
        LatLng boysAndGirls = new LatLng(33.73182, -84.43971);
        LatLng pathwayUpper = new LatLng(33.70866, -84.41853);
        LatLng pavilionOfHope = new LatLng(33.80129, -84.25537);
        LatLng dAndD = new LatLng(33.71747, -84.2521);
        LatLng keepNorthFultonBeautiful = new LatLng(33.96921, -84.3688);
        mMap.addMarker(new MarkerOptions().position(AFDStation4).title("AFD Station 4").snippet("(404) 555 - 3456"));
        mMap.addMarker(new MarkerOptions().position(boysAndGirls).title("Boys and Girls Club").snippet("(404) 555 - 1234"));
        mMap.addMarker(new MarkerOptions().position(pathwayUpper).title("Pathway Upper").snippet("(404) 555 - 5432"));
        mMap.addMarker(new MarkerOptions().position(pavilionOfHope).title("Pavilion of Hope").snippet("(404) 555 - 8765"));
        mMap.addMarker(new MarkerOptions().position(dAndD).title("D&D").snippet("(404) 555 - 9876"));
        mMap.addMarker(new MarkerOptions().position(keepNorthFultonBeautiful).title("Keep North Fulton Beautiful").snippet("(770) 555 - 7321"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AFDStation4));
    }
}
