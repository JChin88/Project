package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface GetDonationItemFSOptionsPresenter extends BasePresenter {

    void setView(GetDonationItemFSOptionsView getDonationItemFSOptionsView);

    void getDonationItemFSOptions(String locationName);

    interface GetDonationItemFSOptionsView extends BaseView {

        void setUpRecyclerView(FirestoreRecyclerOptions<DonationItem> options);
    }

}
