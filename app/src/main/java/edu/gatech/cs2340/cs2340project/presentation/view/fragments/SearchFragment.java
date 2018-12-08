package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.LocationData;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter2;

public class SearchFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();;
    private CollectionReference donationItemRef = db.collection("Donation Items");;
    private CollectionReference donationLocationRef;

    private DonationItemsAdapter defaultAdapter;
    private DonationItemsAdapter searchAdapter;

    private ImageButton searchBtn;
    private TextInputEditText searchWord;
    private View RL;

    RecyclerView recyclerView;
    List<String> listLocationName;
    List<DonationItem> listDI;

    private ProgressBar progressBar;

    RadioGroup radioGroup;
    RadioButton radioButton;

    private Spinner locationSpinner;
    private Spinner categorySpinner;

    ListenerRegistration listen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.test_recycler_view123);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        readLocationData();
        searchBtn = view.findViewById(R.id.search_btn);
        searchWord = view.findViewById(R.id.edit_text_search);
        radioGroup = view.findViewById(R.id.radio_group_btn);
        radioGroup.check(R.id.radio_btn_location_name);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
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

        listLocationName  = new ArrayList<>();
        for (Location location: LocationData.getLocationList()) {
            listLocationName.add(location.getName());
        }
        listLocationName.add(0, "ALL");
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listLocationName);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner = view.findViewById(R.id.spinner_DI_location_search);
        locationSpinner.setAdapter(adapterLocation);

        ArrayAdapter<DonationItem.DonationItemCategory> adapterCategory = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, DonationItem.DonationItemCategory.values());
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner = view.findViewById(R.id.spinner_DI_category_search);
        categorySpinner.setAdapter(adapterCategory);
        categorySpinner.setVisibility(View.GONE);
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

    public void checkRadioButton() {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = RL.findViewById(radioID);
    }

    public void setUpRecyclerView() {
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

<<<<<<< HEAD
    //    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (adapter != null) {
//            adapter.stopListening();
//        }
//    }
=======
    @Override
    public void onStop() {
        super.onStop();
        if (defaultAdapter != null) {
            defaultAdapter.stopListening();
        }
    }
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

    public void readLocationData() {
        InputStream locationDataFile = getResources().openRawResource(R.raw.location_data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(locationDataFile, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                Location tempLocation = new Location();

                //Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
                // 0    1   2       3           4               5   6   7   8       9   10
                tempLocation.setName(part[1]);
                tempLocation.setLatitude(Double.parseDouble(part[2]));
                tempLocation.setLongtitude(Double.parseDouble(part[3]));
                tempLocation.setAddress(part[4] + ", " + part[5] +  ", " + part[6] + ", " + part[7]);
                tempLocation.setType(part[8]);
//              tempLocation.setPhoneNumber(convertStringPhoneNumber(part[9]));
                tempLocation.setPhoneNumber(part[9]);
                tempLocation.setWebsite(part[10]);
                LocationData.addLocation(Integer.parseInt(part[0]),tempLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
