package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.mvc.controller.LocationList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout userHomeDrawerLayout;
    ActionBarDrawerToggle toggle;
    private TextView welcomeM;

    private String userID;
    private String userName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home Page");
        toolbar = findViewById(R.id.user_tool_bar);
        setSupportActionBar(toolbar);

        userHomeDrawerLayout = findViewById(R.id.user_drawer_layout);

        toggle = new ActionBarDrawerToggle(this, userHomeDrawerLayout,
                toolbar, R.string.user_navigation_drawer_open, R.string.user_navigation_drawer_close);
        userHomeDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        userHomeDrawerLayout.bringToFront();

//        welcomeM = findViewById(R.id.welcomeMessage);
        Intent tempIntent = getIntent();
        userID = tempIntent.getStringExtra("userID");
        userName = tempIntent.getStringExtra("userName");

        String welcomeMessage = userName + " welcome to your application activity screen!";
        //welcomeM.setText(welcomeMessage);

        NavigationView user_view = findViewById(R.id.user_nav_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.user_bottom_navigation);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.my_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

//        NavigationUI.setupActionBarWithNavController(this,navHostFragment.getNavController(), userHomeDrawerLayout);
//        NavigationUI.setupWithNavController(user_view, navHostFragment.getNavController());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    private NavigationView.OnNavigationItemSelectedListener sideNav = new NavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.nav_log_out:
//                    logout();
//                    break;
//            }
//            userHomeDrawerLayout.closeDrawer(GravityCompat.START);
//            return true;
//        }
//    };

    public void logout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
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
        Intent moveToInfo = new Intent(MainActivity.this, UserInfoActivities.class);
        moveToInfo.putExtra("userID", userID);
        MainActivity.this.startActivity(moveToInfo);
    }

    public void onLogoutPress(View view) {
        Intent moveToLogout = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(moveToLogout);
    }

    public void onLocationData(View view) {
        Intent moveToLoginData = new Intent(MainActivity.this, LocationList.class);
        MainActivity.this.startActivity(moveToLoginData);
    }
}
