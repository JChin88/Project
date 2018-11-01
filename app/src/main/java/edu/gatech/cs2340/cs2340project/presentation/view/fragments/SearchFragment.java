package edu.gatech.cs2340.cs2340project.presentation.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddDonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.DonationItemListActivities;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;

public class SearchFragment extends Fragment {

    private FirebaseFirestore db;
    private CollectionReference donationItemRef;

    private DonationItemsAdapter adapter;

    private ImageButton searchBtn;
    private TextInputEditText searchWord;
    private View RL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RL = view;
        setUpRecyclerView(view, "");
        searchBtn = view.findViewById(R.id.search_btn);
        searchWord = view.findViewById(R.id.edit_text_search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchWord.getText().toString().trim();
                onStop();
                setUpRecyclerView(RL, searchText);
            }
        });
    }

    //public void fireStoreSeac

    public void setUpRecyclerView(View view, String searchText) {
        db = FirebaseFirestore.getInstance();
        donationItemRef = db.collection("Donation Items");
        Query query;
        if (searchText.length() > 0) {
            query = donationItemRef.whereEqualTo("donationItemName", searchText);
        } else {
            query = db.collection("Donation Items");
            Log.d("Test", "Success");
        }
//        DonationItem.DonationItemCategory category = DonationItem.DonationItemCategory.valueOf("CLOTHES");
//        query = searchByCategory(category);

        FirestoreRecyclerOptions<DonationItem> options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();
        adapter = new DonationItemsAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.donation_item_recycler_view_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
                String id = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + id;
                //Pass the id into the next info
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), AddDonationItem.class);
                intent.putExtra(AddDonationItem.EXTRA_ID, id);
//                intent.putExtra("Request", EDIT_DONATION_ITEM_REQUEST);
                startActivity(intent);
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
