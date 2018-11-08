package edu.gatech.cs2340.cs2340project.mvc.controller;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivityOld extends AppCompatActivity {
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    FirebaseAuth mAuth;
    UserDataRepository mUserData;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private ProgressBar mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        // populateAutoComplete();

        mAuth = FirebaseAuth.getInstance();


//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
////                    attemptLogin();
//                    return true;
//                }
//                return false;
//            }
//        });

//        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
//        mEmailSignInButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id = mEmailView.getText().toString();
//                String password = mPasswordView.getText().toString();
//                if (loginMatch(id, password)) {
//                    Intent moveToApplication = new Intent(LoginActivityOld.this, ApplicationActivity.class);
//
//                    //Pass user data into the application activity
//                    HashMap<String, User> tempUser = UserData.getUserList();
//                    moveToApplication.putExtra("name", tempUser.get(id).getName());
//                    moveToApplication.putExtra("userID", tempUser.get(id).getID());
//
//                    LoginActivityOld.this.startActivity(moveToApplication);
//                } else {
//                    Snackbar invalidLogin = Snackbar.make(mProgressView, "User Login Information Invalid", 800);
//                    invalidLogin.show();
//                }
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

        }

    }

//    //TODO update to scan a list of user data for correct information
//    private boolean loginMatch(String id, String password) {
//        HashMap<String, Integer> loginData = UserData.getLoginData();
//        if (loginData.containsKey(id)) {
//            Integer passHash = loginData.get(id).hashCode();
//            if (passHash.equals(password.hashCode())) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private void login() {
//        String email = mEmailView.getText().toString().trim();
//        String password = mPasswordView.getText().toString().trim();
//
//        if (!isInputValid(email, password)) {
//            return;
//        }
//        mProgressView.setVisibility(View.VISIBLE);
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        mProgressView.setVisibility(View.GONE);
//                        if (task.isSuccessful()) {
//                            finish();
//                            Intent moveToApplication = new Intent(LoginActivityOld.this, ApplicationActivity.class);
//                            User user = mUserData.getUser(mAuth.getCurrentUser().getUid());
//                            String name;
//                            name = user.getName();
//                            //Pass user data into the application activity
//                            //HashMap<String, User> tempUser = UserData.getUserList();
//                            //moveToApplication.putExtra("name", tempUser.get(id).getName());
//                            //moveToApplication.putExtra("userID", tempUser.get(id).getID());
//                            moveToApplication.putExtra("userID", user.getID());
//
//                            LoginActivityOld.this.startActivity(moveToApplication);
//                        } else {
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException
//                                    || task.getException() instanceof FirebaseAuthInvalidUserException) {
//                                Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//
//                    }
//                });
//    }
//
//    private boolean isInputValid(String email, String password) {
//        if (email.isEmpty()) {
//            mEmailView.setError("Email is empty. Please insert an email");
//            mEmailView.requestFocus();
//            return false;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            mEmailView.setError("Please enter a valid email");
//            mEmailView.requestFocus();
//            return false;
//        }
//
//        if (password.isEmpty()) {
//            mPasswordView.setError("Password is empty. Please insert a password");
//            mPasswordView.requestFocus();
//            return false;
//        }
//
//        if (password.length() < 6) {
//            mPasswordView.setError("Minimum length of password should be 6");
//            mPasswordView.requestFocus();
//            return false;
//        }
//
//        return true;
//    }

//    public void onLoginPressOld(View view) {
//        login();
//    }
//
//    /**
//     * Button for cancel - go back to the welcome screen
//     *
//     * @param view
//     */
//    public void onCancelPressOld(View view) {
//        try {
//            mAuth.signOut();
//            Intent moveBackToWelcome = new Intent(LoginActivityOld.this, Welcome.class);
//            LoginActivityOld.this.startActivity(moveBackToWelcome);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}