package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface GetDonationItemPresenter extends BasePresenter {

    void setView(GetDonationItemView getDonationItemView);

    void getDonationItem(String donationItemID);

    interface GetDonationItemView extends BaseView {

        void displayDonationItemDetails(DonationItem donationItem);
    }
}
