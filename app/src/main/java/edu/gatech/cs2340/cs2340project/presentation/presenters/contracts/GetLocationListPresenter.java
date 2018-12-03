package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * @author  Hoa V Luu
 */
public interface GetLocationListPresenter extends BasePresenter {

    void setView(LocationListView locationListView);

    void getLocationList();

    interface LocationListView extends BaseView {

        /**
         * show message when get location list success
         * @param successMessage success message
         */
        void showSucessMessage(String successMessage);

    }
}
