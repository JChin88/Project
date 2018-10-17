package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.prensentation.ui.activities.UserInfo;

public class ApplicationActivity extends AppCompatActivity{

    private TextView welcomeM;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        welcomeM = findViewById(R.id.welcomeMessage);
        Intent tempIntent = getIntent();
        String userName = tempIntent.getStringExtra("name");
        userID = tempIntent.getStringExtra("userID");

        String welcomeMessage = userName + " welcome to your application activity screen!";
        welcomeM.setText(welcomeMessage);
    }

    public void onUserInfoPress(View view) {
        Intent moveToInfo = new Intent(ApplicationActivity.this, UserInfo.class);
        moveToInfo.putExtra("userID", userID);
        ApplicationActivity.this.startActivity(moveToInfo);
    }

    public void onLogoutPress(View view) {
        Intent moveToLogout = new Intent(ApplicationActivity.this, Welcome.class);
        ApplicationActivity.this.startActivity(moveToLogout);
    }

    public void onLocationData(View view) {
        Intent moveToLoginData = new Intent(ApplicationActivity.this, LocationList.class);
        ApplicationActivity.this.startActivity(moveToLoginData);
    }
}