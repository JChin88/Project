package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.cs2340project.R;

public class itemAdderActivity extends AppCompatActivity {



    private Spinner catSpin;
    private EditText shortDescriptionField;
    private EditText locationOfDonationField;
    private EditText fullDescriptionField;
    private EditText valueField;

    private DonationItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adder);

        //category spinner
        catSpin = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpin.setAdapter(adapter);

        //fields
        shortDescriptionField = (EditText) findViewById(R.id.shortDescription);
        valueField = (EditText) findViewById(R.id.value);
        fullDescriptionField = (EditText) findViewById(R.id.fullDescription);
        locationOfDonationField = (EditText) findViewById(R.id.location);
        
    }

    public void onAddPressed(View view) {
        item.setShortDescription(shortDescriptionField.getText().toString());
        item.setLongDescription(locationOfDonationField.getText().toString());
        item.setValue(valueField.getText().toString());
        item.setLocationOfDonationField(locationOfDonationField.getText().toString());
        item.setCategory(catSpin.getSelectedItem());


    }



    //TODO: delete class
    private class DonationItem {
    }
}
