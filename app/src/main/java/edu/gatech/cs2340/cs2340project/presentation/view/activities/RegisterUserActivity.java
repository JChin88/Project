package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.AddUserPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.AddUserPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class RegisterUserActivity extends AppCompatActivity implements AddUserPresenter.RegisterView {

    TextInputEditText userNameView;
    TextInputEditText userEmailView;
    EditText userPasswordView;
    EditText userConfirmPasswordView;
    Spinner userTypeSpinner;
    ProgressBar registrationProgress;
    Button registerBtn;
    Button cancelBtn;

    LinearLayout registerLL;

    private AddUserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Initialise
        registerLL = findViewById(R.id.linear_layout_register_user);
        userNameView = findViewById(R.id.UserName);
        userEmailView = findViewById(R.id.UserEmail);
        userPasswordView = findViewById(R.id.UserPassword);
        userConfirmPasswordView = findViewById(R.id.confirmPassword);
        userTypeSpinner = findViewById(R.id.userType);
        registrationProgress = findViewById(R.id.registration_progress);
        registerBtn = findViewById(R.id.register_user_btn);
        cancelBtn = findViewById(R.id.register_cancel_btn);
        /*
            Set up the adapter user types to display the allowable user types in the spinner
         */
        ArrayAdapter<User.AccountType> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, User.AccountType.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapterUserType);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterPress(v);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveBackLogin = new Intent(RegisterUserActivity.this, LoginActivity.class);
                RegisterUserActivity.this.startActivity(moveBackLogin);
            }
        });

        //
        setTitle("Registration");
    }

    @Override
    public void showProgress() {
        registrationProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        registrationProgress.setVisibility(View.GONE);
    }

    @Override
    public void showViewRetry() {
        registerLL.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        registerLL.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(RegisterUserActivity.this, errorMessage, Toast.LENGTH_LONG).show();
    }


    @Override
    public void moveToLogin() {
        Intent moveToLogin = new Intent(RegisterUserActivity.this, LoginActivity.class);
        RegisterUserActivity.this.startActivity(moveToLogin);
    }

    @Override
    public void showSuccessMessage(String successMessage) {
        Toast.makeText(RegisterUserActivity.this, successMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isInputValid(String name, String email, String password) {
        if (name.isEmpty()) {
            userNameView.setError("Name is empty. Please insert a name");
            userNameView.requestFocus();
            return false;
        }

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

    public void onRegisterPress(View view) {
        showProgress();
        hideViewRetry();
        String userName = userNameView.getText().toString().trim();
        String userEmail = userEmailView.getText().toString().trim();
        String userPassword = userPasswordView.getText().toString().trim();
        User.AccountType userType = User.AccountType.valueOf(userTypeSpinner.getSelectedItem().toString());
        if (isInputValid(userName, userEmail, userPassword)) {
            mPresenter = new AddUserPresenterImpl(ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new UserDataRepository(),
                    userName,
                    userEmail,
                    userPassword,
                    userType);
            mPresenter.resume();
        } else {
            hideProgress();
            showViewRetry();
        }
    }
}
