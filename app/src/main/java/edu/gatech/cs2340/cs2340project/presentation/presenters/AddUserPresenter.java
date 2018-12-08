package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * Add user presenter
 */
public interface AddUserPresenter extends BasePresenter {

    interface RegisterView extends BaseView {

        /**
         * move to login after add user sucess
         */
        void moveToLogin();

        /**
         * show a message to UI to let user know the process success
         * @param successMessage success method
         */
        void showSuccessMessage(String successMessage);

        /**
         * Check user input for new user information
         * @param name user name
         * @param email user email
         * @param password user password
         * @return true if pass of the conditiions
         */
        boolean isInputValid(String name, String email, String password);
    }
}
