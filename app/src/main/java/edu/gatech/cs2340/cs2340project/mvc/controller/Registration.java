package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserData;
import edu.gatech.cs2340.cs2340project.domain.model.User;

public class Registration extends AppCompatActivity {

    TextInputEditText userNameView;
    TextInputEditText userIDView;
    TextInputEditText userEmailView;
    EditText userPasswordView;
    EditText userConfirmPasswordView;
    Spinner userTypeSpinner;
    ProgressBar registrationProgress;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private User _user;
//    private UserData _userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialise
        userNameView = findViewById(R.id.UserName);
        userIDView = findViewById(R.id.UserID);
        userEmailView = findViewById(R.id.UserEmail);
        userPasswordView = findViewById(R.id.UserPassword);
        userConfirmPasswordView = findViewById(R.id.confirmPassword);
        userTypeSpinner = findViewById(R.id.userType);
        registrationProgress = findViewById(R.id.registration_progress);

        //
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        /*
            Set up the adapter user types to display the allowable user types in the spinner
         */
        ArrayAdapter<User.AccountType> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, User.AccountType.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapterUserType);

        //
        setTitle("Registration");
    }

    private boolean isInputValid(String email, String password) {
        if (email.isEmpty()) {
            userEmailView.setError("Email is empty. Please insert an email");
            userEmailView.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmailView.setError("Please enter a valid email");
            userEmailView.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            userPasswordView.setError("Password is empty. Please insert a password");
            userPasswordView.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            userPasswordView.setError("Minimum length of password should be 6");
            userPasswordView.requestFocus();
            return false;
        }

        return true;
    }

    private void registerUser() {
        String email = userEmailView.getText().toString().trim();
        String password = userPasswordView.getText().toString().trim();

        if (!isInputValid(email, password)) {
            return;
        }

        registrationProgress.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                registrationProgress.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    User user = new User(userNameView.getText().toString().trim(),
                            userIDView.getText().toString().trim(),
                            userEmailView.getText().toString().trim());
                    db.collection("users").document(mAuth.getCurrentUser().getUid()).set(user);
                    Toast.makeText(getApplicationContext(), "User Registered Successful", Toast.LENGTH_LONG).show();
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Email already registered!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    /**
     *
     * @param view
     */
    public void onRegistratePress(View view) {
//        String password = userPasswordView.getText().toString();
//        String confirmPassword = userConfirmPasswordView.getText().toString();
//
//        if (!password.equals(confirmPassword)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
//            builder.setMessage("The password and confirm password doesn't match.")
//                    .setNegativeButton("Retry", null)
//                    .create()
//                    .show();
//        }
//
//        _user = new User(userNameView.getText().toString(),
//                userIDView.getText().toString(),
//                userPasswordView.getText().toString(),
//                userEmailView.getText().toString(),
//                (User.AccountType) userTypeSpinner.getSelectedItem());
//
//        if (UserData.addUser(_user)) {
//            Intent moveToLogin = new Intent(Registration.this, LoginActivity.class);
//            Registration.this.startActivity(moveToLogin);
//        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
//            builder.setMessage("The username already exist.")
//                    .setNegativeButton("Retry", null)
//                    .create()
//                    .show();
//        }
        registerUser();

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
