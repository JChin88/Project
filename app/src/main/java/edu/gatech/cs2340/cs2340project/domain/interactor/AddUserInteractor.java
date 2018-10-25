package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;

public interface AddUserInteractor extends Interactor {

    interface Callback {
        void onAddUser(User user);

        void onAddUserFailed(String errorMessage);
    }
}
