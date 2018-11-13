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

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.LocationData;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddDonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.DonationItemListActivities;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter2;

public class SearchFragment extends Fragment {

    private FirebaseFirestore db;
    private CollectionReference donationItemRef;
    private CollectionReference donationLocationRef;

    private DonationItemsAdapter2 adapter;

    private ImageButton searchBtn;
    private TextInputEditText searchWord;
    private View RL;
    private RecyclerView recyclerView;
    private List<String> listLocationName;
    private List<DonationItem> listDI;

    private ProgressBar progressBar;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private Spinner locationSpinner;
    private Spinner categorySpinner;

    private ListenerRegistration listen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readLocationData();
        db = FirebaseFirestore.getInstance();
        searchBtn = view.findViewById(R.id.search_btn);
        searchWord = view.findViewById(R.id.edit_text_search);
        radioGroup = view.findViewById(R.id.radio_group_btn);
        progressBar = view.findViewById(R.id.search_progressbar);
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
                setUpRecyclerView(searchText);

            }
        });
        listDI = new ArrayList<>();
        RL = view;
        recyclerView = view.findViewById(R.id.donation_item_recycler_view_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DonationItemsAdapter2(getActivity(), listDI);
        recyclerView.setAdapter(adapter);
        setUpRecyclerView("");
//        donationLocationRef.orderBy("name", Query.Direction.ASCENDING)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                for (DocumentSnapshot querySnapshot: task.getResult()) {
//                    listLocationName.add(querySnapshot.toObject(Location.class).getName());
//                }
//                listLocationName.add(0, "ALL");
//                ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listLocationName);
//                adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                locationSpinner.setAdapter(adapterLocation);
//
//                setUpRecyclerView("");
//            }
//        });

    }

    private void checkRadioButton() {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = RL.findViewById(radioID);
    }

    //public void fireStoreSeac


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_donation_item, menu);
        MenuItem searchMI = menu.findItem(R.id.search_donation_item_btn);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMI);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchMI.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setUpRecyclerView(String searchText) {
        checkRadioButton();
        progressBar.setVisibility(View.VISIBLE);
        donationItemRef = db.collection("Donation Items");
        String typeSearch = radioButton.getText().toString();
        String location = locationSpinner.getSelectedItem().toString();
        String category = categorySpinner.getSelectedItem().toString();
        Query query = donationItemRef.orderBy("donationItemName", Query.Direction.ASCENDING);
        if ("Location Name".equals(typeSearch)) {
            if (!searchText.isEmpty()) {
                if ("ALL".equals(location)) {
                    query = donationItemRef.whereEqualTo("donationItemName", searchText)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                } else {
                    query = donationItemRef.whereEqualTo("donationItemName", searchText)
                            .whereEqualTo("locationName", location)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                }
            } else {
                if ("ALL".equals(location)) {
                    query =  donationItemRef.orderBy("donationItemName", Query.Direction.ASCENDING);
                } else {
                    query = donationItemRef.whereEqualTo("locationName", location)
                            .orderBy("donationItemName", Query.Direction.ASCENDING);
                }
            }
        } else if ("Category".equals(typeSearch)) {
            query = db.collection("Donation Items").whereEqualTo("category", category)
                    .orderBy("donationItemName", Query.Direction.ASCENDING);
            if (!searchWord.getText().toString().trim().isEmpty()) {
                searchWord.setText("");
            }
        }
//        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                listDI.clear();
//                if (task.isSuccessful()) {
//                    //listDI = queryDocumentSnapshots.toObjects(DonationItem.class);
//                    for (DocumentSnapshot documentSnapshot: task.getResult()) {
//                        listDI.add(documentSnapshot.toObject(DonationItem.class));
//                    }
//                }
//                adapter.notifyDataSetChanged();
//
//                //adapter = new DonationItemsAdapter2(getActivity(), listDI);
//                //recyclerView.setAdapter(adapter);
//                progressBar.setVisibility(View.GONE);
//            }
//        });
         listen = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                                @javax.annotation.Nullable FirebaseFirestoreException e) {
                Log.d("Test", "Search btn");
                listDI.clear();
                if (e == null) {
                    //listDI = queryDocumentSnapshots.toObjects(DonationItem.class);
                    listDI.addAll(queryDocumentSnapshots.toObjects(DonationItem.class));
                } else {
                    Toast.makeText(getActivity(), "No Items Match the Search", Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();

                if (listen != null) {
                    listen.remove();
                }

                //adapter = new DonationItemsAdapter2(getActivity(), listDI);
                //recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }
        });

////        DonationItem.DonationItemCategory category = DonationItem.DonationItemCategory.valueOf("CLOTHES");
////        query = searchByCategory(category);
//
//        FirestoreRecyclerOptions<DonationItem> options = new FirestoreRecyclerOptions.Builder<DonationItem>()
//                .setQuery(query, DonationItem.class)
//                .build();
//        adapter = new DonationItemsAdapter(options);
//
//        RecyclerView recyclerView = view.findViewById(R.id.donation_item_recycler_view_search);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
//                DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
//                String id = documentSnapshot.getId();
//                String message = "Position: " + position + "ID: " + id;
//                //Pass the id into the next info
//                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(getActivity(), AddDonationItem.class);
//                intent.putExtra(AddDonationItem.EXTRA_ID, id);
////                intent.putExtra("Request", EDIT_DONATION_ITEM_REQUEST);
//                startActivity(intent);
//            }
//        });

    }

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

    private void readLocationData() {
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
                tempLocation.setLongitude(Double.parseDouble(part[3]));
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
