package edu.gatech.cs2340.cs2340project.presentation.view.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationLocationViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends DaggerFragment implements OnMapReadyCallback, BaseView {

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DonationLocationViewModel donationLocationViewModel;

    private MapView mapView;

    public MapFragment() {
        // Required empty public constructor
    }

    private List<DonationLocation> donationLocationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        donationLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(DonationLocationViewModel.class);
        donationLocationViewModel.response().observe(this, response -> processResponse(response));
        final View fragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map_fragment);
        donationLocationViewModel.getLocationList();
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                hideViewRetry();
                showProgress();
                break;

            case SUCCESS:
                hideProgress();
                donationLocationList = (List<DonationLocation>) response.data;
                if (mapView !=null && donationLocationList != null && !donationLocationList.isEmpty()) {
                    mapView.onCreate(null);
                    mapView.onResume();
                    mapView.getMapAsync(this);
                }
                break;

            case ERROR:
                hideProgress();
                showError(response.error.getMessage());
                showViewRetry();
                break;
        }
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
        LatLng firstLatLng = new LatLng(donationLocationList.get(0).getLatitude(),
                donationLocationList.get(0).getLongitude());
        // Add a markers and move the camera
        for (DonationLocation donationLocation: donationLocationList) {
            LatLng locationLatLng = new LatLng(donationLocation.getLatitude(),
                    donationLocation.getLongitude());
            String donationLocationName = donationLocation.getName();
            String donationLocationPhoneNumber = donationLocation.getPhoneNumber();
            googleMap.addMarker(new MarkerOptions()
                    .position(locationLatLng)
                    .title(donationLocationName)
                    .snippet(donationLocationPhoneNumber));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(firstLatLng));
    }

    @Override
    public void showProgress() {
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showViewRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        donationLocationViewModel.getLocationList();
    }
}
