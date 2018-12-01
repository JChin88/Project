package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfoPresenter.View;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.UserInfoPresenterImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class UserInfoActivities extends AppCompatActivity implements View {

    private TextView mUserID;
    private TextView mUserName;
    private UserInfoPresenter mPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mUserID = findViewById(R.id.info_userID);
        mUserName = findViewById(R.id.info_userName);
        mProgressBar = findViewById(R.id.user_info_progressBar);

        Intent tempIntent = getIntent();
        String userID;
        userID = tempIntent.getStringExtra("userID");

        // create a presenter for this view
        mPresenter = new UserInfoPresenterImpl(
                userID,
                ThreadExecutorImpl.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserDataRepository()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
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

    }

    @Override
    public void hideViewRetry() {

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayUserInfo(User user) {
        mUserID.setText("User ID is : " + user.getUserID());
        mUserName.setText("User Name is : " + user.getName());
    }
}
