package edu.gatech.cs2340.cs2340project.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import edu.gatech.cs2340.cs2340project.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }


    protected boolean Passcomp() {
        EditText pass1 = (EditText) findViewById(R.id.Password);
        EditText pass2 = (EditText) findViewById(R.id.confirm_password);
        if(pass1.getText().toString().equals(pass2.getText().toString())){
            return true;
        }
        return false;
    }
}
