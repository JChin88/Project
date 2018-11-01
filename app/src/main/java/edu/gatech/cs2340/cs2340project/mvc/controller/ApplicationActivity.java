package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.UserInfoActivities;

public class ApplicationActivity extends AppCompatActivity{

    private DrawerLayout userHomeDrawerLayout;
    private TextView welcomeM;

    private String userID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Toolbar toolbar = findViewById(R.id.user_tool_bar);
        setSupportActionBar(toolbar);

        userHomeDrawerLayout = findViewById(R.id.user_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, userHomeDrawerLayout,
                toolbar, R.string.user_navigation_drawer_open, R.string.user_navigation_drawer_close);
        userHomeDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        welcomeM = findViewById(R.id.welcomeMessage);
        Intent tempIntent = getIntent();
        userID = tempIntent.getStringExtra("userID");
        userName = tempIntent.getStringExtra("userName");

        String welcomeMessage = userName + " welcome to your application activity screen!";
        welcomeM.setText(welcomeMessage);
    }

    @Override
    public void onBackPressed() {
        if (userHomeDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            userHomeDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onUserInfoPress(View view) {
        Intent moveToInfo = new Intent(ApplicationActivity.this, UserInfoActivities.class);
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
