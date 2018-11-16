package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;

/**
 * List of donation items activities
 */
public class DonationItemListActivities extends AppCompatActivity {
    private static final int ADD_DONATION_ITEM_REQUEST = 1;
    private static final int EDIT_DONATION_ITEM_REQUEST = 2;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference donationItemRef = db.collection("Donation Items");

    private RecyclerView recyclerView;
    private DonationItemsAdapter adapter;
//    private DonationItemsAdapter2 adapter2;
//    private List<DonationItem> listDI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_list_activities);

        FloatingActionButton buttonAddNote = findViewById(R.id.add_donation_item_button);
        Intent tempIntent = getIntent();
        final String locationName = tempIntent.getStringExtra("Location Name");

        recyclerView = findViewById(R.id.donation_item_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonationItemListActivities.this, AddDonationItem.class);
                intent.putExtra("Location Name", locationName);
                intent.putExtra("Request", ADD_DONATION_ITEM_REQUEST);
                DonationItemListActivities.this.startActivity(intent);
                //DonationItemListActivities.this.startActivityForResult(intent,
                // ADD_DONATION_ITEM_REQUEST);
            }
        });
        setTitle("Donation Items");
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);
        FirestoreRecyclerOptions.Builder<DonationItem> donationItemBuilder =
                new FirestoreRecyclerOptions.Builder<>();
        donationItemBuilder.setQuery(query, DonationItem.class);
        FirestoreRecyclerOptions<DonationItem> options = donationItemBuilder.build();
        adapter = new DonationItemsAdapter(options);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
                String id = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + id;
                //Pass the id into the next info
                Toast
                        .makeText(DonationItemListActivities.this, message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DonationItemListActivities.this, AddDonationItem.class);
                intent.putExtra(AddDonationItem.EXTRA_ID, id);
                intent.putExtra("Request", EDIT_DONATION_ITEM_REQUEST);
                DonationItemListActivities.this.startActivity(intent);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_DONATION_ITEM_REQUEST && resultCode == RESULT_OK) {
//
//        }
//    }
}
