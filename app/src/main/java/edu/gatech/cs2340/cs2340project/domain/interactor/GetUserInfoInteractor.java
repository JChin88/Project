package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;

public interface GetUserInfoInteractor extends Interactor {

    interface Callback {
        void onUserRetrieved(User user);

        void onRetrievalFailed(String error);
    }
}
