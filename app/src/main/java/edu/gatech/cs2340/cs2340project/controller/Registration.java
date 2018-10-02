package edu.gatech.cs2340.cs2340project.controller;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.model.User;
import edu.gatech.cs2340.cs2340project.model.UserData;

public class Registration extends AppCompatActivity {

    TextInputEditText userNameView;
    TextInputEditText userIDView;
    TextInputEditText userEmailView;
    EditText userPasswordView;
    EditText userConfirmPasswordView;
    Spinner userTypeSpinner;
    View registrationProgress;

    private User _user;
    private UserData _userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialise the
        userNameView = findViewById(R.id.UserName);
        userIDView = findViewById(R.id.UserID);
        userEmailView = findViewById(R.id.UserEmail);
        userPasswordView = findViewById(R.id.UserPassword);
        userConfirmPasswordView = findViewById(R.id.confirmPassword);
        userTypeSpinner = findViewById(R.id.userType);
        registrationProgress = findViewById(R.id.registration_progress);

        /*
            Set up the adapter class standing to display the allowable class standings in the spinner
         */
        ArrayAdapter<User.AccountType> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, User.AccountType.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapterUserType);

    }

    /**
     *
     * @param view
     */
    public void onRegistratePress(View view) {
        String password = userPasswordView.getText().toString();
        String confirmPassword = userConfirmPasswordView.getText().toString();

        if (!password.equals(confirmPassword)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
            builder.setMessage("The password and confirm password doesn't match.")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }

        _user = new User(userNameView.getText().toString(),
                            userIDView.getText().toString(),
                            userPasswordView.getText().toString(),
                            userEmailView.getText().toString(),
                            (User.AccountType) userTypeSpinner.getSelectedItem());

        if (_userData.addUser(_user)) {
            Intent moveToLogin = new Intent(Registration.this, LoginActivity.class);
            Registration.this.startActivity(moveToLogin);
        }

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
