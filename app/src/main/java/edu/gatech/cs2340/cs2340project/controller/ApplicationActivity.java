package edu.gatech.cs2340.cs2340project.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.cs2340project.R;

public class ApplicationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    public void onLogoutPress(View view) {
        Intent moveToLogout = new Intent(ApplicationActivity.this, Welcome.class);
        ApplicationActivity.this.startActivity(moveToLogout);
    }
}
