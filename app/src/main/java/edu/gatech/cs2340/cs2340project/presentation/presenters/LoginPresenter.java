package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface LoginPresenter extends BasePresenter {

    void setView(LoginView view);

    void initialize(String email, String password);

    interface LoginView extends BaseView {

        void moveToUserHomeActivity(String userID);

        boolean isInputValid(String email, String password);
    }

}
