package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationLocationViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class SearchFragment extends DaggerFragment implements BaseView {

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donationItemRef = db.collection("Donation Items");
    private CollectionReference donationLocationRef;

    private DonationItemsAdapter defaultAdapter;
    private DonationItemsAdapter searchAdapter;

    @BindView(R.id.search_btn)
    ImageButton searchBtn;

    @BindView(R.id.edit_text_search)
    TextInputEditText searchWord;

    private View RL;

    @BindView(R.id.test_recycler_view123)
    RecyclerView recyclerView;
    List<String> listLocationName;

    @BindView(R.id.radio_group_btn)
    RadioGroup radioGroup;

    RadioButton radioButton;

    @BindView(R.id.spinner_DI_location_search)
    Spinner locationSpinner;

    @BindView(R.id.spinner_DI_category_search)
    Spinner categorySpinner;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DonationLocationViewModel donationLocationViewModel;

    private List<DonationLocation> donationLocationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        donationLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(DonationLocationViewModel.class);
        donationLocationViewModel.response().observe(this, response -> processResponse(response));
        donationLocationViewModel.getLocationList();
        final View fragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
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
                setUpLocationSpinner();
                break;

            case ERROR:
                hideProgress();
                showError(response.error.getMessage());
                showViewRetry();
                break;
        }
    }

    private void setUpLocationSpinner() {
        listLocationName = new ArrayList<>();
        for (DonationLocation location : donationLocationList) {
            listLocationName.add(location.getName());
        }
        listLocationName.add(0, "ALL");
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, listLocationName);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapterLocation);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        radioGroup.check(R.id.radio_btn_location_name);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn_location_name:
                        locationSpinner.setVisibility(View.VISIBLE);
                        categorySpinner.setVisibility(View.GONE);
                        searchWord.setEnabled(true);
                        break;
                    case R.id.radio_btn_category:
                        locationSpinner.setVisibility(View.GONE);
                        categorySpinner.setVisibility(View.VISIBLE);
                        searchWord.setEnabled(false);
                        break;
                }
            }
        });

        ArrayAdapter<DonationItem.DonationItemCategory> adapterCategory = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, DonationItem.DonationItemCategory.values());
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapterCategory);
        categorySpinner.setVisibility(View.GONE);

        //
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchWord.getText().toString().trim();
                defaultAdapter.stopListening();
                Query query = searchQuery(searchText);
                FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                        .setQuery(query, DonationItem.class)
                        .build();
                searchAdapter = new DonationItemsAdapter(options);
                searchAdapter.startListening();
                recyclerView.setAdapter(searchAdapter);
            }
        });
        RL = view;
        setUpRecyclerView();
    }

    private void checkRadioButton() {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = RL.findViewById(radioID);
    }

    private void setUpRecyclerView() {
        Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();
        defaultAdapter = new DonationItemsAdapter(options);
        recyclerView.setAdapter(defaultAdapter);
    }

    public Query searchQuery(String searchText) {
        checkRadioButton();
        String typeSearch = radioButton.getText().toString();
        String location = locationSpinner.getSelectedItem().toString();
        String category = categorySpinner.getSelectedItem().toString();
        Query query = donationItemRef.orderBy("donationItemName", Query.Direction.ASCENDING);
        if (typeSearch.equals("Location Name")) {
            if (searchText.length() > 0) {
                if (location.equals("ALL")) {
                    query = donationItemRef.whereEqualTo("donationItemName", searchText)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                } else {
                    query = donationItemRef.whereEqualTo("donationItemName", searchText)
                            .whereEqualTo("locationName", location)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                }
            } else {
                if (location.equals("ALL")) {
                    query = donationItemRef.orderBy("donationItemName", Query.Direction.ASCENDING);
                } else {
                    query = donationItemRef.whereEqualTo("locationName", location)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                }
            }
        } else if (typeSearch.equals("Category")) {
            query = db.collection("Donation Items").whereEqualTo("category", category)
                    .orderBy("donationItemName", Query.Direction.ASCENDING);
            if (searchWord.getText().toString().trim().length() > 0) {
                searchWord.setText("");
            }
        }
        return query;
    }

    @Override
    public void onStart() {
        super.onStart();
        defaultAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (defaultAdapter != null) {
            defaultAdapter.stopListening();
        }
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
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
