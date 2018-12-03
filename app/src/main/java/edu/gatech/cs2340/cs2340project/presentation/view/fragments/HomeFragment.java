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
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationLocationAdapter;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference locationRef = db.collection("Donation Locations");

    @BindView(R.id.donation_location_recycler_view)
    RecyclerView recyclerView;

    private DonationLocationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                HomeFragmentDirections.ActionNavHomeToLocationInfo action
                        = HomeFragmentDirections.actionNavHomeToLocationInfo(key);
                Navigation.findNavController(getView()).navigate(action);
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
