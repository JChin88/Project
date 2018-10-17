package edu.gatech.cs2340.cs2340project.prensentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.prensentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.prensentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayUserInfo(User user);
    }
}
