package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view_models.UserViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class UpdateNewUserRights extends DaggerAppCompatActivity implements BaseView {

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    @BindView(R.id.user_type_spinner)
    Spinner userTypeSpinner;

    @BindView(R.id.save_user_rights_btn)
    Button saveUserRightsBtn;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_new_user_rights);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.response().observe(this, response -> processResponse(response));

        ArrayAdapter<UserRights> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UserRights.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapterUserType);

        saveUserRightsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRights userRights = UserRights.valueOf(userTypeSpinner.getSelectedItem().toString());
                userViewModel.addUSerWUR(userRights);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                hideViewRetry();
                showProgress();
                break;

            case SUCCESS:
                hideProgress();
                Toast.makeText(this, (String)response.data, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                UpdateNewUserRights.this.startActivity(intent);
//                finish();
                break;

            case ERROR:
                hideProgress();
                showError(response.error.getMessage());
                showViewRetry();
                break;
        }
    }

    @Override
    public void showProgress() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showViewRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
