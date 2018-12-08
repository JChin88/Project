package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface AddUserPresenter extends BasePresenter {

    void setView(RegisterView registerView);

    void addUser(String name, String email, String password, UserRights userRights);

    interface RegisterView extends BaseView {

        void moveToLogin();

        void showSuccessMessage(String successMessage);

        boolean isInputValid(String name, String email, String password);
    }
}
