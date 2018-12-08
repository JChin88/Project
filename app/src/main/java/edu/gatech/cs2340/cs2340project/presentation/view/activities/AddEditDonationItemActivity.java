package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.databinding.ActivityAddDonationItemBinding;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;
import edu.gatech.cs2340.cs2340project.presentation.view_models.AddEditDonationItemViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationItemDetailsViewModel;

/**
 * Add a donation item into the database
 */
@SuppressWarnings("ChainedMethodCall")
public class AddEditDonationItemActivity extends DaggerAppCompatActivity implements BaseView {

    public static final String EXTRA_LOCATION_NAME =
            "edu.gatech.cs2340.cs2340project.presentation.view.activities.EXTRA_LOCATION_NAME";
    public static final String EXTRA_ID =
            "edu.gatech.cs2340.cs2340project.presentation.view.activities.EXTRA_ID";

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @BindView(R.id.relativeLayout_retry)
    RelativeLayout rl_retry;

    @BindView(R.id.btn_retry)
    Button btn_retry;

    @BindView(R.id.text_view_donation_item_location_name)
    TextView textViewLocationName;

    @BindView(R.id.text_view_donation_item_time_stamp)
    TextView textViewTimeStamp;

    @BindView(R.id.edit_text_donation_item_name)
    EditText editTextDonationItemName;

    @BindView(R.id.edit_text_donation_item_short_description)
    EditText editTextShortDescription;

    @BindView(R.id.edit_text_donation_item_full_description)
    EditText editTextFullDescription;

    @BindView(R.id.edit_text_donation_item_values)
    EditText editTextValue;

    @BindView(R.id.spinner_donation_item_category)
    Spinner spinnerCategory;

    @BindView(R.id.edit_text_donation_item_comments)
    EditText editTextComments;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private AddEditDonationItemViewModel addEditDonationItemViewModel;

    private DonationItemDetailsViewModel donationItemDetailsViewModel;

    private ActivityAddDonationItemBinding binding;

    private String donationItemID;

    private boolean hasDonationItemID = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_donation_item);
        ButterKnife.bind(this);
        //
        Intent intent = getIntent();
        String locationName = intent.getStringExtra(EXTRA_LOCATION_NAME);
        textViewLocationName.setText(locationName);

        //
        addEditDonationItemViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AddEditDonationItemViewModel.class);
        addEditDonationItemViewModel.response().observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    hideViewRetry();
                    showProgress();
                    break;

                case SUCCESS:
                    hideProgress();
                    if (intent.hasExtra(EXTRA_ID)) {
                        Toast.makeText(this, "Donation Item Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Donation Item Added", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case ERROR:
                    hideProgress();
                    showError(response.error.getMessage());
                    showViewRetry();
                    break;
            }
        });

        //
        ArrayAdapter<DonationItem.DonationItemCategory> adapterUserType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, DonationItem.DonationItemCategory.values());
        adapterUserType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterUserType);

        //
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        //
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Donation Item");
            donationItemID = intent.getStringExtra(EXTRA_ID);
            hasDonationItemID = true;
            donationItemDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
                    .get(DonationItemDetailsViewModel.class);
            donationItemDetailsViewModel.response().observe(this, response -> {
                switch (response.status) {
                    case LOADING:
                        hideViewRetry();
                        showProgress();
                        break;

                    case SUCCESS:
                        hideProgress();
                        DonationItem donationItem = (DonationItem) response.data;
                        binding.setDonationItem(donationItem);
                        textViewTimeStamp.setVisibility(View.VISIBLE);
                        spinnerCategory.setSelection(donationItem.getCategory().ordinal());
                        break;

                    case ERROR:
                        hideProgress();
                        showError(response.error.getMessage());
                        showViewRetry();
                        break;
                }
            });
            donationItemDetailsViewModel.getDonationItem(donationItemID);
        } else {
            setTitle("Add Donation Item");
            binding.setDonationItem(new DonationItem());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_new_donation_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_donation_item:
                saveDonationItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean checkValidInput(String name, String shortDescription, String fullDescription,
                                 double values, String comments) {
        if (name.isEmpty()) {
            editTextDonationItemName.setError("Donation item name is empty. Please insert a name");
            editTextDonationItemName.requestFocus();
            return false;
        }

        if (shortDescription.isEmpty()) {
            editTextShortDescription.setError("Short description is empty. Please insert a short description");
            editTextShortDescription.requestFocus();
            return false;
        }

        if (fullDescription.isEmpty()) {
            editTextFullDescription.setError("Full description is empty. Please insert a full description");
            editTextFullDescription.requestFocus();
            return false;
        }

        if (values < 1 ||values > 10000000) {
            editTextValue.setError("Invalid values");
            editTextValue.requestFocus();
            return false;
        }

        if (comments.isEmpty()) {
            editTextComments.setError("Comment is empty. Please insert a comment");
            editTextComments.requestFocus();
            return false;
        }

        return true;
    }

    private void saveDonationItem() {
        String name = editTextDonationItemName.getText().toString().trim();
        String locationName = textViewLocationName.getText().toString().trim();
        String shortDescription = editTextShortDescription.getText().toString().trim();
        String fullDescription = editTextFullDescription.getText().toString().trim();
        double values = Double.parseDouble(editTextValue.getText().toString());
        DonationItem.DonationItemCategory category = DonationItem.DonationItemCategory
                .valueOf(spinnerCategory.getSelectedItem().toString());
        String comments = editTextComments.getText().toString().trim();

        //By Firestore default, null timeStamp will make the firestore trigger the default method
        //With take the time stamp of the server and add it into the object timestamp
        if (checkValidInput(name, shortDescription, fullDescription, values, comments)) {
            DonationItem tempDI = new DonationItem(null, name, locationName,
                    shortDescription, fullDescription, values, category, comments);
            if (hasDonationItemID) {
                addEditDonationItemViewModel.editDonationItem(donationItemID, tempDI);
            } else {
                addEditDonationItemViewModel.addDonationItem(tempDI);
            }
            finish();
        }
    }

    @Override
    public void showProgress() {
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showViewRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        saveDonationItem();
    }
}
