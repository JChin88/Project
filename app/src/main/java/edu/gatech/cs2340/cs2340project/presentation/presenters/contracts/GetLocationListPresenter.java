package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * @author  Hoa V Luu
 */
public interface GetLocationListPresenter extends BasePresenter {

    void setView(LocationListView locationListView);

    void getLocationList();

    interface LocationListView extends BaseView {

        void displayLocationList(FirestoreRecyclerOptions<DonationLocation> options);

    }
}
