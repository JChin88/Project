package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.adapter.DonationItemsAdapter;

public class DonationItemListActivities extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donationItemRef = db.collection("Donation Items");

    private DonationItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_list_activities);

        FloatingActionButton buttonAddNote = findViewById(R.id.add_donation_item_button);
        Intent tempIntent = getIntent();
        final String locationName = tempIntent.getStringExtra("Location Name");

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonationItemListActivities.this, AddDonationItem.class);
                intent.putExtra("Location Name", locationName);
                DonationItemListActivities.this.startActivity(intent);
            }
        });

        setTitle("Donation Items");
        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<DonationItem> options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();

        adapter = new DonationItemsAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.donation_item_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
                String id = documentSnapshot.getId();
                String message =  "Position: " + position + "ID: " + id;
                //Pass the id into the next info
                Toast.makeText(DonationItemListActivities.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}