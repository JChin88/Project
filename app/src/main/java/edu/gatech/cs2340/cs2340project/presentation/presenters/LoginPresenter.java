package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * @author Hoa V Luu
 */
public interface LoginPresenter extends BasePresenter {

    interface LoginView extends BaseView {

        /**
         * move to user home when success get user
         * @param userID userid of current user
         */
        void moveToUserHomeActivity(String userID);

        /**
         * check valid input
         * @param email user email
         * @param password user password
         * @return a boolean indicate if the input is valid or not
         */
        boolean isInputValid(String email, String password);
    }

}
