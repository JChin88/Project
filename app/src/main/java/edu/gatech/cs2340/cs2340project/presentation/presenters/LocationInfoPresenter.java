package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface LocationInfoPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayLocationInfo(Location location);
    }
}
