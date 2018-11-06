package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.content.Intent;
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
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.navigation.Navigation;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.mvc.controller.LocationInfo;
import edu.gatech.cs2340.cs2340project.mvc.controller.LocationList;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.DonationItemListActivities;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.LocationInfoActivities;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.LoginActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationLocationAdapter;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference locationRef = db.collection("Donation Locations");

    private RecyclerView recyclerView;
    private DonationLocationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.donation_location_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        Query query = locationRef.orderBy(("name"), Query.Direction.ASCENDING);
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Location>()
                .setQuery(query, Location.class)
                .build();
        adapter = new DonationLocationAdapter(options);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DonationLocationAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                Location donationLocation = documentSnapshot.toObject(Location.class);
                String key = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + key;
                //Pass the id into the next info
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getContext(), LocationInfoActivities.class);
//                intent.putExtra("key", key);
//                startActivity(intent);
                Navigation.findNavController(getView()).navigate(R.id.action_nav_home_to_locationInfo);
//                Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_locationInfo, null);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
