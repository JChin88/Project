package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * @author Hoa V Luu
 */
public interface LocationInfoPresenter extends BasePresenter {

    interface View extends BaseView {

        /**
         * Display location info to the ui
         * @param location location get back from the repository
         */
        void displayLocationInfo(Location location);
    }
}
