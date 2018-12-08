package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.mvc.controller.LocationList;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.DonationItemListActivities;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.LoginActivity;

public class HomeFragment extends Fragment {

    private Button moveToLocationListBtn;
    private Button moveToInventoryBtn;
    private Button logOut;
=======
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
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

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
<<<<<<< HEAD
=======
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpRecyclerView();
    }
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

        moveToLocationListBtn = view.findViewById(R.id.move_to_location_btn);
        moveToLocationListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationList.class);
                startActivity(intent);
=======
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                Location donationLocation = documentSnapshot.toObject(Location.class);
                String key = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + key;
                //Pass the id into the next info
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                HomeFragmentDirections.ActionNavHomeToLocationInfo action
                        = HomeFragmentDirections.actionNavHomeToLocationInfo(key);
                Navigation.findNavController(getView()).navigate(action);
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
            }
        });

        moveToInventoryBtn = view.findViewById(R.id.move_to_inventory);
        moveToInventoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DonationItemListActivities.class);
                startActivity(intent);
            }
        });
        logOut = view.findViewById(R.id.temp_log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
