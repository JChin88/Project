package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

/**
 * Add user interactor for use case of add new user to the database
 */
public interface AddUserInteractor extends Interactor {

    interface Callback {

        /**
         * after add user success execute this method
         * @param successMessage a success message
         */
        void onAddUser(String successMessage);

        /**
         * after add user fail execute this method
         * @param errorMessage a error emssage
         */
        void onAddUserFailed(String errorMessage);
    }
}
