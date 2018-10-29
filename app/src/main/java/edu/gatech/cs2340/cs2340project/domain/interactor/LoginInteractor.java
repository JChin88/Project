package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

public interface LoginInteractor extends Interactor {

    interface Callback {

        void onLoginSuccess(String userID);

        void onLoginFailed(String errorMessage);
    }

}
