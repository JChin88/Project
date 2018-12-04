package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface LocationInfoPresenter extends BasePresenter {

    void setView(LocationInfoView locationInfoView);

    void initialize(String key);

    interface LocationInfoView extends BaseView {
        void displayLocationInfo(DonationLocation location);
    }
}
