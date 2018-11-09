package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface GetLocationListPresenter extends BasePresenter {

    interface LocationListView extends BaseView {

        void showSucessMessage(String successMessage);

    }
}
