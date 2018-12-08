package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter.LoginView;
import edu.gatech.cs2340.cs2340project.presentation.view_models.UserViewModel;

public class LoginActivity extends DaggerAppCompatActivity implements LoginView {

    private static final int RC_SIGN_IN = 123;
    //    private static final Object TAG = ;

    @BindView(R.id.login_email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.login_password)
    EditText mPasswordView;

    @BindView(R.id.login_linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.sign_in_btn)
    Button loginButton;

    @BindView(R.id.login_register_btn)
    Button registerButton;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout relativeLayoutProgress;

    @Inject
    LoginPresenter mPresenter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.response().observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    hideViewRetry();
                    showProgress();
                    break;

                case SUCCESS:
                    hideProgress();
                    moveToUserHomeActivity(((User)response.data).getUserID());
//                finish();
                    break;

                case ERROR:
                    hideProgress();
                    showError(response.error.getMessage());
                    showViewRetry();
                    break;
            }
        });
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.AnonymousBuilder().build()))
                .enableAnonymousUsersAutoUpgrade()
                .setIsSmartLockEnabled(false)
                .build();
        startActivityForResult(intent, RC_SIGN_IN);

        //
        mPresenter.setView(this);
        //
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hide the keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToRegister = new Intent(LoginActivity.this,
                        RegisterUserActivity.class);
                LoginActivity.this.startActivity(moveToRegister);
            }
        });
        setTitle("Login");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                if (idpResponse.isNewUser()) {
                    Intent intent = new Intent(this, UpdateNewUserRights.class);
                    startActivity(intent);
                } else {
                    userViewModel.getCurrentUser();
                }
//                String idp = idpResponse.getIdpToken();
//                FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                String id = mAuth.getCurrentUser().getUid();
//                Toast.makeText(LoginActivity.this, "User IDP " + id, Toast.LENGTH_LONG).show();
//                moveToUserHomeActivity(id);
//                startActivity(new Intent(this, WelcomeBackActivity.class)
//                        .putExtra("my_token", idpResponse.getIdpToken()));
//                finish();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
//                    showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
//                    showSnackbar(R.string.no_internet_connection);
                    return;
                }

//                showSnackbar(R.string.unknown_error);
//                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }

    //Login
    public void loginAuthUI() {
                Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.AnonymousBuilder().build()))
                .enableAnonymousUsersAutoUpgrade()
                .setIsSmartLockEnabled(false)
                .build();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void showProgress() {
        relativeLayoutProgress.setVisibility(View.VISIBLE);
        setProgressBarIndeterminateVisibility(true);
//        mProgressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        intent.putExtra("userID", userID);
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

}
