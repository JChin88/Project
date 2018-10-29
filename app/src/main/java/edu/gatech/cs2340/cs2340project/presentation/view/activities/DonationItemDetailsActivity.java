package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;

import static edu.gatech.cs2340.cs2340project.presentation.view.activities.AddDonationItem.EXTRA_ID;

public class DonationItemDetailsActivity extends AppCompatActivity {

    private LinearLayout ll_DIDetails;
    private TextView textViewDITimestamp;
    private TextView textViewDIName;
    private TextView textViewDILocationName;
    private TextView textViewShortDescription;
    private TextView textViewFullDescription;
    private TextView textViewValues;
    private TextView textViewCategory;
    private TextView textViewComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_details);
        setTitle("Donation Item Details");

        ll_DIDetails = findViewById(R.id.linear_layout_donation_item_details);
        textViewDITimestamp = findViewById(R.id.text_view_donation_item_time_stamp);
        textViewDIName = findViewById(R.id.text_view_donation_item_name);
        textViewDILocationName = findViewById(R.id.text_view_donation_item_location_name1);
        textViewShortDescription = findViewById(R.id.text_view_donation_item_short_description);
        textViewFullDescription = findViewById(R.id.text_view_donation_item_full_description);
        textViewValues = findViewById(R.id.text_view_donation_item_values);
        textViewCategory = findViewById(R.id.text_view_donation_item_category);
        textViewComments = findViewById(R.id.text_view_donation_item_comments);
        ll_DIDetails.setVisibility(View.GONE);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            String id = intent.getStringExtra(EXTRA_ID);

            DocumentReference tempDIRef = db.collection("Donation Items").document(id);
            tempDIRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    DonationItem tempDI = documentSnapshot.toObject(DonationItem.class);
                    textViewDITimestamp.setText(tempDI.getTimeStamp().toString());
                    textViewDIName.setText(tempDI.getDonationItemName());
                    textViewDILocationName.setText(tempDI.getLocationName());
                    textViewShortDescription.setText(tempDI.getShortDescription());
                    textViewFullDescription.setText(tempDI.getFullDescription());
                    textViewValues.setText(Double.toString(tempDI.getValue()));
                    textViewCategory.setText(tempDI.getCategory().toString());
                    textViewComments.setText(tempDI.getComments());
                    ll_DIDetails.setVisibility(View.VISIBLE);
                }
            });
        } else {
            ll_DIDetails.setVisibility(View.GONE);
            return;
        }

    }
}
