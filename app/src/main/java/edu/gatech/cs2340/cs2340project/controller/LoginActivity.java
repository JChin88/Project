package edu.gatech.cs2340.cs2340project.controller;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.model.User;
import edu.gatech.cs2340.cs2340project.model.UserData;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
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

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        // populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
//                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                User login = new User(id, password);
                if (loginMatch(login)) {
                    Intent moveToApplication = new Intent(LoginActivity.this, ApplicationActivity.class);
                    LoginActivity.this.startActivity(moveToApplication);
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    //TODO update to scan a list of user data for correct information
    private boolean loginMatch(User currentUser) {
        if (currentUser.getID().equals("user") && currentUser.getPassword().equals("password")) {
            return true;
        }
        return false;
    }

    /**
     * Button for cancel - go back to the welcome screen
     *
     * @param view
     */
    public void onCancelPress(View view) {
        Intent moveBackToWelcome = new Intent(LoginActivity.this, Welcome.class);
        LoginActivity.this.startActivity(moveBackToWelcome);
    }
}