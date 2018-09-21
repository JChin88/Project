package edu.gatech.cs2340.cs2340project.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.cs2340project.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Button handle
     * @param view
     */
    public void onLoginPress(View view) {
        Intent moveToLogin = new Intent(Welcome.this, LoginActivity.class);
        Welcome.this.startActivity(moveToLogin);
    }

    /**
     *
     * @param view
     */
    public void onRegisterPress(View view) {
        Intent moveToRegister = new Intent(Welcome.this, null);
        Welcome.this.startActivity(moveToRegister);
    }
}
