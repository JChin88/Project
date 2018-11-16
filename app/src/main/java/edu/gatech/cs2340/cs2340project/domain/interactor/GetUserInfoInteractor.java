package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;

/**
 * @author Hoa V Luu
 */
public interface GetUserInfoInteractor extends Interactor {

    interface Callback {
        /**
         * next action for success retrieved user
         * @param user user get from repository
         */
        void onUserRetrieved(User user);

        /**
         * display a erro message
         * @param error error message want to display
         */
        void onRetrievalFailed(String error);
    }
}
