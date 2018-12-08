package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LocationInfoPresenter;


public class LocationInfoFragment extends DaggerFragment implements LocationInfoPresenter.LocationInfoView {


    //Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
    // 0    1   2       3           4               5   6   7   8       9   10

    @BindView(R.id.location_name_text_view)
     TextView locationName;

    @BindView(R.id.location_latitude_text_view)
     TextView locationLatitude;

    @BindView(R.id.location_longitude_text_view)
     TextView locationLongitude;

    @BindView(R.id.location_address_text_view)
     TextView locationAddress;

    @BindView(R.id.location_type_text_view)
     TextView locationType;

    @BindView(R.id.location_phone_text_view)
     TextView locationPhone;

    @BindView(R.id.location_website_text_view)
     TextView locationWebsite;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.location_inventory_btn)
    Button inventoryBtn;

     @Inject
     LocationInfoPresenter mPresenter;

     private String locationNameString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_location_info, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().setTitle("Location Information");
        mPresenter.setView(this);
        String key = LocationInfoFragmentArgs.fromBundle(getArguments()).getLocationID();
        mPresenter.initialize(key);

        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationInfoFragmentDirections.ActionLocationInfoToLocationInventory action
                        = LocationInfoFragmentDirections
                        .actionLocationInfoToLocationInventory(locationNameString);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        rl_progress.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rl_progress.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showViewRetry() {
//        linearLayout.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
//        linearLayout.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
    }

    @Override
    public void displayLocationInfo(Location location) {
        locationNameString = location.getName();
        locationName.setText("Location Name: \t" + location.getName());
        locationLatitude.setText("Location Latitude: \t" + Double.toString(location.getLatitude()));
        locationLongitude.setText("Location Longitude: \t" + Double.toString(location.getLongitude()));
        locationAddress.setText("Location Address: \t" + location.getAddress());
        locationType.setText("Location Type: \t" + location.getType());
        locationPhone.setText("Location Phone Number: \t" + location.getPhoneNumber());
        locationWebsite.setText("Location Website: \t" + location.getWebsite());
    }
}
