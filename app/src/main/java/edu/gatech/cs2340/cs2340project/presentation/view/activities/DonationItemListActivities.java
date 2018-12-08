package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter;
import edu.gatech.cs2340.cs2340project.presentation.view.adapters.DonationItemsAdapter2;

public class DonationItemListActivities extends AppCompatActivity {
    public static final int ADD_DONATION_ITEM_REQUEST = 1;
    public static final int EDIT_DONATION_ITEM_REQUEST = 2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donationItemRef = db.collection("Donation Items");

    private RecyclerView recyclerView;
    private DonationItemsAdapter adapter;
    private DonationItemsAdapter searchAdapter;
    private DonationItemsAdapter2 adapter2;
    private List<DonationItem> listDI;
    Query query;
    FirestoreRecyclerOptions<DonationItem> options;

    int number = 2;

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
                adapter.stopListening();
                Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.DESCENDING).limit(number);
                FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                        .setQuery(query, DonationItem.class)
                        .build();
                searchAdapter = new DonationItemsAdapter(options);
                searchAdapter.startListening();
                recyclerView.setAdapter(searchAdapter);
                number++;
//                Intent intent = new Intent(DonationItemListActivities.this, AddDonationItem.class);
//                intent.putExtra("Location Name", locationName);
//                intent.putExtra("Request", ADD_DONATION_ITEM_REQUEST);
//                DonationItemListActivities.this.startActivity(intent);
//                //DonationItemListActivities.this.startActivityForResult(intent, ADD_DONATION_ITEM_REQUEST);
            }
        });
        setTitle("Donation Items");
        setUpRecyclerView();
    }

    public void searchByCategory(DonationItem.DonationItemCategory category) {
        String stringCategory = category.toString();
        Query query = donationItemRef.whereEqualTo("category", stringCategory)
                .orderBy("donationItemName", Query.Direction.ASCENDING);
        options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();
    }

    public void setUpRecyclerView() {
<<<<<<< HEAD
        Query query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);
//        DonationItem.DonationItemCategory category = DonationItem.DonationItemCategory.valueOf("CLOTHES");
//        query = searchByCategory(category);

=======
        query = donationItemRef.orderBy(("donationItemName"), Query.Direction.ASCENDING);
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
        options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                .setQuery(query, DonationItem.class)
                .build();
        adapter = new DonationItemsAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DonationItemsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
                String id = documentSnapshot.getId();
                String message = "Position: " + position + "ID: " + id;
                //Pass the id into the next info
                Toast.makeText(DonationItemListActivities.this, message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DonationItemListActivities.this, AddDonationItem.class);
                intent.putExtra(AddDonationItem.EXTRA_ID, id);
                intent.putExtra("Request", EDIT_DONATION_ITEM_REQUEST);
                DonationItemListActivities.this.startActivity(intent);
            }
        });

    }

    public void searchItem(String name) {
        if (name.length() < 1) {
            setUpRecyclerView();
            return;
        }
        name = name.trim();
        listDI = new ArrayList<>();
        Query query = donationItemRef.whereEqualTo("donationItemName", name)
                .orderBy("donationItemName", Query.Direction.ASCENDING);
        options = new FirestoreRecyclerOptions.Builder<DonationItem>().setQuery(query, DonationItem.class). build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_donation_item, menu);
        MenuItem searchMI = menu.findItem(R.id.search_donation_item_btn);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMI);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() < 1) {
                    setUpRecyclerView();
                    return false;
                }
                searchItem(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() < 1) {
                    setUpRecyclerView();
                    return false;
                }
                searchItem(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
