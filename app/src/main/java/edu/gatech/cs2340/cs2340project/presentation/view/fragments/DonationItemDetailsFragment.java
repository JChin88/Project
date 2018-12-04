package edu.gatech.cs2340.cs2340project.presentation.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemPresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddDonationItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationItemDetailsFragment extends DaggerFragment implements GetDonationItemPresenter.GetDonationItemView{

    @BindView(R.id.donation_item_time_stamp_text_view)
    TextView donationItemTimeStamp;

    @BindView(R.id.donation_item_name_text_view)
    TextView donationItemName;

    @BindView(R.id.donation_item_location_name_text_view)
    TextView donationItemLocationName;

    @BindView(R.id.donation_item_short_description_text_view)
    TextView donationItemShortDescription;

    @BindView(R.id.donation_item_full_description_text_view)
    TextView donationItemFullDescription;

    @BindView(R.id.donation_item_values_text_view)
    TextView donationItemValues;

    @BindView(R.id.donation_item_category_text_view)
    TextView donationItemCategory;
    @BindView(R.id.donation_item_comments_text_view)
    TextView donationItemComments;

    @BindView(R.id.relativeLayout_progress)
    RelativeLayout rl_progress;

    @Inject
    GetDonationItemPresenter getDonationItemPresenter;

    private String locationName;

    public DonationItemDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_donation_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.donation_item_edit_btn) {
            Intent intent = new Intent(getContext(), AddDonationItem.class);
            intent.putExtra("DonationLocation Name", locationName);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater
                .inflate(R.layout.fragment_donation_item_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDonationItemPresenter.setView(this);
        String donationItemID = DonationItemDetailsFragmentArgs.fromBundle(getArguments()).getDonationItemID();
        getDonationItemPresenter.getDonationItem(donationItemID);
    }

    private String makeDisplayText(String string, String valueString) {
        String tempString = string + ": \t" + valueString;
        return tempString;
    }

    @Override
    public void displayDonationItemDetails(DonationItem donationItem) {
        locationName = donationItem.getLocationName();
        donationItemTimeStamp.setText(makeDisplayText("Time Stamp", donationItem.getTimeStamp().toString()));
        donationItemName.setText(makeDisplayText("Name", donationItem.getDonationItemName()));
        donationItemLocationName.setText(makeDisplayText("DonationLocation Name", donationItem.getLocationName()));
        donationItemShortDescription.setText(makeDisplayText("Short Description", donationItem.getShortDescription()));
        donationItemFullDescription.setText(makeDisplayText("Full Description", donationItem.getFullDescription()));
        donationItemValues.setText(makeDisplayText("Values", Double.toString(donationItem.getValue())));
        donationItemCategory.setText(makeDisplayText("Category", donationItem.getCategory().toString()));
        donationItemComments.setText(makeDisplayText("Comments:", donationItem.getComments()));

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

    }

    @Override
    public void hideViewRetry() {

    }

    @Override
    public void showError(String errorMessage) {

    }
}
