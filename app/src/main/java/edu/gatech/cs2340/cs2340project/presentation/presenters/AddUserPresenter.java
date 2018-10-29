package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface AddUserPresenter extends BasePresenter {

    interface RegisterView extends BaseView {

        void moveToLogin();

        void showSuccessMessage(String successMessage);

        boolean isInputValid(String name, String email, String password);
    }
}
