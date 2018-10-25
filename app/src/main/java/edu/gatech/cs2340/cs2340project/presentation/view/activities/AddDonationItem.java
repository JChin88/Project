package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;

public class AddDonationItem extends AppCompatActivity {

    public static final String EXTRA_ID =
            "edu.gatech.cs2340.cs2340project.presentation.view.activities.EXTRA_ID";

    private TextView textViewTimeStamp;
    private EditText editTextDonationItemName;
    private TextView textViewLocationName;
    private EditText editTextShortDescription;
    private EditText editTextFullDescription;
    private EditText editTextValue;
    private Spinner spinnerCategory;
    private EditText editTextComments;

    private String locationName;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation_item);



        textViewTimeStamp = findViewById(R.id.text_view_donation_item_time_stamp);
        editTextDonationItemName = findViewById(R.id.edit_text_donation_item_name);
        textViewLocationName = findViewById(R.id.text_view_donation_item_location_name);
        editTextShortDescription = findViewById(R.id.edit_text_donation_item_short_description);
        editTextFullDescription = findViewById(R.id.edit_text_donation_item_full_description);
        editTextValue = findViewById(R.id.edit_text_donation_item_values);
        spinnerCategory = findViewById(R.id.spinner_donation_item_catogory);
        editTextComments = findViewById(R.id.edit_text_donation_item_comments);

        textViewLocationName.setText("Location Name: " + getIntent().getStringExtra("Location Name"));



        if (textViewTimeStamp.getText().toString().equals("Time Stamp")) {
            textViewTimeStamp.setVisibility(View.GONE);
        }

        ArrayAdapter<DonationItem.DonationItemCategory> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, DonationItem.DonationItemCategory.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterUserType);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Donation Item");
            DocumentReference dS = db.collection("Donation Items").document(intent.getStringExtra(EXTRA_ID));
            dS.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    DonationItem tempDI = documentSnapshot.toObject(DonationItem.class);
                    textViewTimeStamp.setText(tempDI.getTimeStamp());
                    textViewTimeStamp.setVisibility(View.VISIBLE);
                    textViewTimeStamp.setText(tempDI.getTimeStamp());
                    editTextDonationItemName.setText(tempDI.getDonationItemName());
                    textViewLocationName.setText(tempDI.getLocationName());
                    editTextShortDescription.setText(tempDI.getShortDescription());
                    editTextFullDescription.setText(tempDI.getFullDescription());
                    editTextValue.setText(Double.toString(tempDI.getValue()));
                    spinnerCategory.setSelection(tempDI.getCategory().ordinal());
                    editTextComments.setText(tempDI.getComments());
                }
            });
        } else {
            setTitle("Add Donation Item");
        }



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

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(time * 1000);
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.toString();
    }

//    private DonationItem setDonationItem() {
//
//    }

    private void saveDonationItem() {
        //Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis()));
        //String currentTime = timestamp.toString();
        String currentTime = getDate(System.currentTimeMillis());
        textViewTimeStamp.setText("Time Stamp: " + currentTime);
        String title = editTextDonationItemName.getText().toString();
        String locationName = textViewLocationName.getText().toString();
        String shortDescription = editTextShortDescription.getText().toString();
        String fullDescription = editTextFullDescription.getText().toString();
        double values = Double.parseDouble(editTextValue.getText().toString());
        DonationItem.DonationItemCategory category =  DonationItem.DonationItemCategory
                .valueOf(spinnerCategory.getSelectedItem().toString());
        String comments = editTextComments.getText().toString();

        DonationItem tempDI = new DonationItem(currentTime, title, locationName, shortDescription, fullDescription, values, category, comments);

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a donation item title", Toast.LENGTH_LONG).show();
            return;
        }

        if (getIntent().hasExtra(EXTRA_ID)) {
            DocumentReference dr = FirebaseFirestore.getInstance()
                    .collection("Donation Items").document(getIntent().getStringExtra(EXTRA_ID));
            dr.set(tempDI, SetOptions.merge());
            Toast.makeText(this, "Donation Item Updated", Toast.LENGTH_SHORT).show();
        } else {
            CollectionReference donationItemsRef = FirebaseFirestore.getInstance().collection("Donation Items");
            donationItemsRef.add(tempDI);
            Toast.makeText(this, "Donation Item Added", Toast.LENGTH_SHORT).show();
        }
        finish();

    }
}
