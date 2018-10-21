package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface LoginPresenter extends BasePresenter {

    interface View extends BaseView {

        void moveToUserHomeActivity(User user);

        boolean isInputValid(String email, String password);
    }

}
