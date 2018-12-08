package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.util.IntentUtil;

public class MainActivity extends DaggerAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String ARGUMENT_CURRENT_USER_ID = "CURRENT_USER_ID";

    @BindView(R.id.user_drawer_layout)
    DrawerLayout userHomeDrawerLayout;
    @BindView(R.id.user_tool_bar)
    Toolbar toolbar;

    ActionBarDrawerToggle toggle;

    @Inject
    @Nullable
    User currentUser;

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser == null) {
            IntentUtil.moveBackLogin(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Home Page");
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, userHomeDrawerLayout,
                toolbar, R.string.user_navigation_drawer_open, R.string.user_navigation_drawer_close);
        userHomeDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        userHomeDrawerLayout.bringToFront();

        NavigationView user_view = findViewById(R.id.user_nav_view);
        user_view.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.user_bottom_navigation);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.my_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        IntentUtil.moveBackLogin(MainActivity.this);
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        MainActivity.this.startActivity(intent);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_map:
                Intent goToMap = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(goToMap);
                break;
            case R.id.nav_log_out:
                logout();
                break;
        }
        userHomeDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
