package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface GetLocationListPresenter extends BasePresenter {

    void setView(LocationListView locationListView);

    void getLocationList();

    interface LocationListView extends BaseView {

        void showSucessMessage(String successMessage);

    }
}
