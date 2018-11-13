package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter.LoginView;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LoginPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class LoginActivity extends AppCompatActivity implements LoginView {

    //@BindView fields must not be private or static.
    @BindView(R.id.login_email)
     AutoCompleteTextView mEmailView;

    @BindView(R.id.login_password)
     EditText mPasswordView;

    @BindView(R.id.login_progress)
     ProgressBar mProgressBar;

    @BindView(R.id.login_linear_layout)
     LinearLayout linearLayout;

    @BindView(R.id.sign_in_btn)
     Button loginButton;

    @BindView(R.id.login_register_btn)
     Button registerButton;

    private LoginPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide the keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                onLoginPress(v);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterPress(v);
            }
        });
        setTitle("Login");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(android.view.View.GONE);
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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

    private void onLoginPress(View view) {
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

    private void onRegisterPress(View view) {
            Intent moveToRegister = new Intent(LoginActivity.this,
                    RegisterUserActivity.class);
            LoginActivity.this.startActivity(moveToRegister);
    }
}
