package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.LocationDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LocationInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LocationInfoPresenter.View;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LocationInfoPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class LocationInfoActivities extends AppCompatActivity implements View {

    //Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
    // 0    1   2       3           4               5   6   7   8       9   10

    private TextView locationName;
    private TextView locationLatitude;
    private TextView locationLongtitude;
    private TextView locationAddress;
    private TextView locationType;
    private TextView locationPhone;
    private TextView locationWebsite;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private LocationInfoPresenter mPresenter;

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
        progressBar = findViewById(R.id.progress_bar_donation_location_details);
        linearLayout = findViewById(R.id.linear_layout_donation_location_details);
        Intent tempIntent = getIntent();
        String key = tempIntent.getStringExtra("key");

        setTitle("Location Information");

        // create a presenter for this view
        mPresenter = new LocationInfoPresenterImpl(
                key,
                ThreadExecutorImpl.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new LocationDataRepository()
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showViewRetry() {
        linearLayout.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        linearLayout.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(LocationInfoActivities.this, message, Toast.LENGTH_LONG);
    }

    @Override
    public void displayLocationInfo(Location location) {
        locationName.setText("Location Name: \t" + location.getName());
        locationLatitude.setText("Location Latitude: \t" + Double.toString(location.getLatitude()));
        locationLongtitude.setText("Location Longtitude: \t" + Double.toString(location.getLongitude()));
        locationAddress.setText("Location Address: \t" + location.getAddress());
        locationType.setText("Location Type: \t" + location.getType());
        locationPhone.setText("Location Phone Number: \t" + location.getPhoneNumber());
        locationWebsite.setText("Location Website: \t" + location.getWebsite());
    }
}
