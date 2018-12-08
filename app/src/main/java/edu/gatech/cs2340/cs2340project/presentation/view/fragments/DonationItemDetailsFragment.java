package edu.gatech.cs2340.cs2340project.presentation.view.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.databinding.FragmentDonationItemDetailsBinding;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationItemDetailsViewModel;

import static edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity.EXTRA_ID;
import static edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity.EXTRA_LOCATION_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationItemDetailsFragment extends DaggerFragment implements BaseView {

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    private String donationItemID;
    private String locationName;


    FragmentDonationItemDetailsBinding binding;

    public DonationItemDetailsFragment() {
        // Required empty public constructor
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DonationItemDetailsViewModel donationItemDetailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_donation_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.donation_item_edit_btn) {
            Intent intent = new Intent(getContext(), AddEditDonationItemActivity.class);
            intent.putExtra(EXTRA_LOCATION_NAME, locationName);
            intent.putExtra(EXTRA_ID, donationItemID);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        donationItemDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DonationItemDetailsViewModel.class);
         binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_donation_item_details,
                        container, false);
        donationItemID = DonationItemDetailsFragmentArgs.fromBundle(getArguments()).getDonationItemID();
        donationItemDetailsViewModel.response().observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    hideViewRetry();
                    showProgress();
                    break;

                case SUCCESS:
                    hideProgress();
                    binding.setDonationItem((DonationItem) response.data);
                    locationName = ((DonationItem) response.data).getLocationName();
                    break;

                case ERROR:
                    hideProgress();
                    showError(response.error.getMessage());
                    showViewRetry();
                    break;
            }
        });
        donationItemDetailsViewModel.getDonationItem(donationItemID);
        final View fragmentView = binding.getRoot();
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        donationItemDetailsViewModel.getDonationItem(donationItemID);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showProgress() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showViewRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {

    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        donationItemDetailsViewModel.getDonationItem(donationItemID);
    }
}
