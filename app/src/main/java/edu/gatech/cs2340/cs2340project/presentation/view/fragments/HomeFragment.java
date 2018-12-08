package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationLocationViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationLocationAdapter;

public class HomeFragment extends DaggerFragment implements BaseView {

    @BindView(R.id.donation_location_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    @Nullable
    User user;

    private DonationLocationAdapter adapter;

    private DonationLocationViewModel donationLocationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, fragmentView);
        donationLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(DonationLocationViewModel.class);
        donationLocationViewModel.response().observe(this, response -> processResponse(response));
        donationLocationViewModel.getLocationOptions();
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getLocationListPresenter.setView(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        getLocationListPresenter.getLocationList();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }

    public void displayLocationList(FirestoreRecyclerOptions<DonationLocation> options) {
        adapter = new DonationLocationAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.setOnItemClickListener(new DonationLocationAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                DonationLocation donationLocation = documentSnapshot.toObject(DonationLocation.class);
                String key = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + key;
                //Pass the id into the next info
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                HomeFragmentDirections.ActionNavHomeToLocationInfo action
                        = HomeFragmentDirections.actionNavHomeToLocationInfo(key);
                Navigation.findNavController(getView()).navigate(action);
            }
        });
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                hideViewRetry();
                showProgress();
                break;

            case SUCCESS:
                hideProgress();
                displayLocationList((FirestoreRecyclerOptions<DonationLocation>) response.data);
                break;

            case ERROR:
                hideProgress();
                showError(response.error.getMessage());
                showViewRetry();
                break;
        }
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

    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        donationLocationViewModel.getLocationOptions();
    }
}
