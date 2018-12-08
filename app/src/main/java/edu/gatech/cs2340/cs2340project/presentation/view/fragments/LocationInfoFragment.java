package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.databinding.FragmentLocationInfoBinding;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view_models.LocationInfoViewModel;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LocationInfoPresenter;


public class LocationInfoFragment extends DaggerFragment implements BaseView {

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    @BindView(R.id.location_inventory_btn)
    Button inventoryBtn;

    @Inject
    LocationInfoPresenter mPresenter;

    private String locationName;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    @Nullable
    User currentUser;

    private LocationInfoViewModel locationInfoViewModel;

    private String key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLocationInfoBinding binding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_location_info, container, false);
        locationInfoViewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationInfoViewModel.class);
        locationInfoViewModel.response().observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    hideViewRetry();
                    showProgress();
                    break;

                case SUCCESS:
                    hideProgress();
                    binding.setDonationLocation((DonationLocation) response.data);
                    locationName = ((DonationLocation) response.data).getName();
                    break;

                case ERROR:
                    hideProgress();
                    showError(response.error.getMessage());
                    showViewRetry();
                    break;
            }
        });
        key = LocationInfoFragmentArgs.fromBundle(getArguments()).getLocationID();
        locationInfoViewModel.getLocationInfo(key);
        final View fragmentView = binding.getRoot();
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationInfoFragmentDirections.ActionLocationInfoToLocationInventory action
                        = LocationInfoFragmentDirections
                        .actionLocationInfoToLocationInventory(locationName);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.resume();
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
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        locationInfoViewModel.getLocationInfo(key);
    }
}
