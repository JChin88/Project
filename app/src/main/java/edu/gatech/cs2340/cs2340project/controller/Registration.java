package edu.gatech.cs2340.cs2340project.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.cs2340project.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     *
     * @param view
     */
    public void onRegistratePress(View view) {

    }

    /**
     *
     * @param view
     */
    public void onCancelPress(View view) {
        Intent moveBackWelcome = new Intent(Registration.this, Welcome.class);
        Registration.this.startActivity(moveBackWelcome);
    }
}
