package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
<<<<<<< HEAD
import butterknife.OnClick;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.mvc.controller.ApplicationActivity;
import edu.gatech.cs2340.cs2340project.mvc.controller.Welcome;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter.LoginView;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LoginPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;
=======
import dagger.android.support.DaggerAppCompatActivity;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter.LoginView;
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

public class LoginActivity extends DaggerAppCompatActivity implements LoginView {

<<<<<<< HEAD
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private ProgressBar mProgressBar;

    private LinearLayout linearLayout;

    private LoginPresenter mPresenter;
    private LoginPresenter.LoginView loginView;
=======
    @BindView(R.id.welcomeMessage)
    TextView textViewWelcomeMessage;

    @BindView(R.id.login_email)
    AutoCompleteTextView mEmailView;
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

    @BindView(R.id.login_password)
    EditText mPasswordView;

    @BindView(R.id.login_linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.sign_in_btn)
    Button loginButton;
<<<<<<< HEAD
    Button cancelButton;

=======

    @BindView(R.id.login_register_btn)
    Button registerButton;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout relativeLayoutProgress;

    @Inject
    LoginPresenter mPresenter;

>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
<<<<<<< HEAD
        //ButterKnife.bind(this);

        linearLayout = findViewById(R.id.email_login_form);

        String userEmail = "henry@gmail.com";
        String userPassword = "password";
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressBar = findViewById(R.id.login_progress);
        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.email_sign_in_button);
        cancelButton = findViewById(R.id.cancel_button);

=======
        ButterKnife.bind(this);
        mPresenter.setView(this);
        //
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide the keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //
                hideViewRetry();
                showProgress();
                String userEmail = mEmailView.getText().toString().trim();
                String userPassword = mPasswordView.getText().toString().trim();
                if (isInputValid(userEmail, userPassword)) {
                    mPresenter.login(userEmail, userPassword);
                } else {
                    hideProgress();
                    showViewRetry();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                onCancelPress(v);
=======
                Intent moveToRegister = new Intent(LoginActivity.this,
                        RegisterUserActivity.class);
                LoginActivity.this.startActivity(moveToRegister);
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
            }
        });
        setTitle("Login");
    }

    @Override
<<<<<<< HEAD
=======
    protected void onStart() {
        super.onStart();
    }

    @Override
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    public void showProgress() {
        textViewWelcomeMessage.setVisibility(View.VISIBLE);
        relativeLayoutProgress.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);
//        mProgressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        textViewWelcomeMessage.setVisibility(View.GONE);
        relativeLayoutProgress.setVisibility(View.GONE);
        setProgressBarIndeterminateVisibility(false);
//        mProgressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showViewRetry() {
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void moveToUserHomeActivity(String userID) {
        Intent intent = new Intent(LoginActivity.this, ApplicationActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("userName", "Henry");
        LoginActivity.this.startActivity(intent);
    }

    @Override
    public boolean isInputValid(String email, String password) {
        if (email.isEmpty()) {
            mEmailView.setError("Email is empty. Please insert an email");
            mEmailView.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError("Please enter a valid email");
            mEmailView.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            mPasswordView.setError("Password is empty. Please insert a password");
            mPasswordView.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            mPasswordView.setError("Minimum length of password should be 6");
            mPasswordView.requestFocus();
            return false;
        }

        return true;
    }

<<<<<<< HEAD
    public void onLoginPress(View view) {
        hideViewRetry();
        showProgress();
        String userEmail = mEmailView.getText().toString().trim();
        String userPassword = mPasswordView.getText().toString().trim();
        if (isInputValid(userEmail, userPassword)) {
            mPresenter = new LoginPresenterImpl(userEmail,
                    userPassword,
                    ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                     this,
                    new UserDataRepository());
            mPresenter.resume();
        } else {
            hideProgress();
            showViewRetry();
        }

    }

    /**
     * Button for cancel - go back to the welcome screen
     *
     */
    public void onCancelPress(View view) {
            Intent moveBackToWelcome = new Intent(LoginActivity.this, Welcome.class);
            LoginActivity.this.startActivity(moveBackToWelcome);
    }
=======
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
}
