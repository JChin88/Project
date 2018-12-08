package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemFSOptionsPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemFSOptionsPresenter.GetDonationItemFSOptionsView;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;

import static edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity.EXTRA_LOCATION_NAME;

public class LocationInventoryFragment extends DaggerFragment implements GetDonationItemFSOptionsView {

    @BindView(R.id.location_inventory_donation_item_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.location_inventory_add_donation_item_button)
    FloatingActionButton addDonationItemBtn;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @Inject
    GetDonationItemFSOptionsPresenter getDonationItemFSOptionsPresenter;

    private DonationItemsAdapter donationItemsAdapter;

    @Inject
    @Nullable
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater
                .inflate(R.layout.fragment_location_inventory, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String locationName = LocationInventoryFragmentArgs
                .fromBundle(getArguments()).getLocationName();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addDonationItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEditDonationItemActivity.class);
                intent.putExtra(EXTRA_LOCATION_NAME, locationName);
                startActivity(intent);
            }
        });
        if (currentUser != null && currentUser.isCanUpdateInventories()) {
            addDonationItemBtn.setVisibility(View.VISIBLE);
        }
        getDonationItemFSOptionsPresenter.setView(this);
        getDonationItemFSOptionsPresenter.getDonationItemFSOptions(locationName);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (donationItemsAdapter != null) {
            donationItemsAdapter.startListening();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (donationItemsAdapter != null) {
            donationItemsAdapter.stopListening();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getDonationItemFSOptionsPresenter.destroy();
    }

    @Override
    public void setUpRecyclerView(FirestoreRecyclerOptions<DonationItem> options) {
        donationItemsAdapter = new DonationItemsAdapter(options);
        donationItemsAdapter.startListening();
        recyclerView.setAdapter(donationItemsAdapter);
        donationItemsAdapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                String donationItemID = documentSnapshot.getId();
                LocationInventoryFragmentDirections.ActionLocationInventoryToDonationItemDetails
                        action = LocationInventoryFragmentDirections.actionLocationInventoryToDonationItemDetails(donationItemID);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
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

    }

    @Override
    public void hideViewRetry() {

    }

    @Override
    public void showError(String errorMessage) {

    }

}
