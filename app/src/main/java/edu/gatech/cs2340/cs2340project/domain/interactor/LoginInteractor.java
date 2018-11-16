package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

/**
 * @author Hoa V Luu
 */
public interface LoginInteractor extends Interactor {

    interface Callback {

        /**
         * next action when login success
         * @param userID the userid for next action
         */
        void onLoginSuccess(String userID);

        /**
         * Show error message
         * @param errorMessage error message want to display
         */
        void onLoginFailed(String errorMessage);
    }

}
