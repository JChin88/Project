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

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.presentation.presenters.AddUserPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.AddUserPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class RegisterUserActivity extends AppCompatActivity
        implements AddUserPresenter.RegisterView {

    //@BindView fields must not be private or static.
    @BindView(R.id.register_user_name_edit_text)
    TextInputEditText userNameView;

    @BindView(R.id.register_user_email_edit_text)
    TextInputEditText userEmailView;

    @BindView(R.id.register_user_password_edit_text)
    EditText userPasswordView;

    @BindView(R.id.register_user_confirm_password_edit_text)
    EditText userConfirmPasswordView;

    @BindView(R.id.register_account_type_spinner)
    Spinner userTypeSpinner;

    @BindView(R.id.registration_progress)
    ProgressBar registrationProgress;

    @BindView(R.id.register_user_btn)
    Button registerBtn;

    @BindView(R.id.register_cancel_btn)
    Button cancelBtn;

    @BindView(R.id.register_linear_layout)
    LinearLayout registerLL;

    private AddUserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        /*
            Set up the adapter user types to display the allowable user types in the spinner
         */
        ArrayAdapter<UserRights> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UserRights.values());
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
                Intent moveBackLogin =
                        new Intent(RegisterUserActivity.this, LoginActivity.class);
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

    private void onRegisterPress(View view) {
        showProgress();
        hideViewRetry();
        String userName = userNameView.getText().toString().trim();
        String userEmail = userEmailView.getText().toString().trim();
        String userPassword = userPasswordView.getText().toString().trim();
        UserRights userRights = UserRights.valueOf(userTypeSpinner.getSelectedItem().toString());
        if (isInputValid(userName, userEmail, userPassword)) {
            mPresenter = new AddUserPresenterImpl(ThreadExecutor.getInstance(),
                    MainThreadImpl.getInstance(),
                    this,
                    new UserDataRepository(),
                    userName,
                    userEmail,
                    userPassword,
                    userRights);
            mPresenter.resume();
        } else {
            hideProgress();
            showViewRetry();
        }
    }
}
