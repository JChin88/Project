package edu.gatech.cs2340.cs2340project.presentation.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donationItemRef = db.collection("Donation Items");

    private RecyclerView recyclerView;
    private DonationItemsAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.test_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();
        adapter = new DonationItemsAdapter(options);
        recyclerView.setAdapter(adapter);
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
