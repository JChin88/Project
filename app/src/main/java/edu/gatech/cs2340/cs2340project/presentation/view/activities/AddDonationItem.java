package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;

public class AddDonationItem extends AppCompatActivity {

    private EditText editTextDonationItemName;

    private String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation_item);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Donation Item");

        editTextDonationItemName = findViewById(R.id.edit_text_donation_item_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu__new_donation_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_donation_item:
                saveDonationItem();
                return true;

                default:
                    return super.onOptionsItemSelected(item);

        }
    }

    private void saveDonationItem() {
        String title = editTextDonationItemName.getText().toString();
        String locationName = getIntent().getStringExtra("Location Name");
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a donation item title", Toast.LENGTH_LONG).show();
            return;
        }

        CollectionReference donationItemsRef = FirebaseFirestore.getInstance().collection("Donation Items");
        donationItemsRef.add(new DonationItem(title, locationName, "Test123"));
        Toast.makeText(this, "Donation Item Added", Toast.LENGTH_SHORT).show();
        finish();

    }
}
