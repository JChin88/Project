package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.data.UserInfoRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserData;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfo.View;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.UserInfoImpl;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

public class UserInfoActivities extends AppCompatActivity implements View {

    private TextView mMessage;
    private TextView mUserID;
    private TextView mUserName;
    private edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfo mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mMessage = findViewById(R.id.info_message);
        mUserID = findViewById(R.id.info_userID);
        mUserName = findViewById(R.id.info_userName);


        UserData.addUser(new User());
        UserData.addUser(new User("abc", "123"));
        UserData.addUser(new User("abc123", "123"));

        Intent tempIntent = getIntent();

        // create a presenter for this view
        mPresenter = new UserInfoImpl(
                tempIntent.getStringExtra("userID"),
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserInfoRepository()
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
        mMessage.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mMessage.setText(message);
    }

    @Override
    public void displayUserInfo(User user) {
        mUserID.setText("User ID is : " + user.getID());
        mUserName.setText("User Name is : " + user.getName());
        mMessage.setText("Retrieved sucess!");
    }
}
