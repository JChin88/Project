package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.mvc.controller.ApplicationActivity;
import edu.gatech.cs2340.cs2340project.mvc.controller.Welcome;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter.View;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LoginPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class LoginActivity extends AppCompatActivity implements View {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private ProgressBar mProgressBar;

    private LoginPresenter mPresenter;

    private FirebaseAuth mAuth;

    @BindView(R.id.login_button) Button loginButton;
    @BindView(R.id.cancel_button) Button cancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ButterKnife.bind(this);

        String userEmail = "henry@gmail.com";
        String userPassword = "password";
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressBar = findViewById(R.id.login_progress);
        mAuth = FirebaseAuth.getInstance();

        mPresenter = new LoginPresenterImpl(userEmail,
                userPassword,
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserDataRepository());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.resume();
        }
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

    @OnClick(R.id.login_button)
    public void onLoginPress() {
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
        }
        mPresenter.resume();
    }

    /**
     * Button for cancel - go back to the welcome screen
     *
     */
    @OnClick(R.id.cancel_button)
    public void onCancelPress() {
            Intent moveBackToWelcome = new Intent(LoginActivity.this, Welcome.class);
            LoginActivity.this.startActivity(moveBackToWelcome);
    }
}
