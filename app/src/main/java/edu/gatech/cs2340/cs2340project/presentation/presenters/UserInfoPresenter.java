package edu.gatech.cs2340.cs2340project.presentation.presenters;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

/**
 * @author Hoa V Luu
 */
public interface UserInfoPresenter extends BasePresenter {

    interface View extends BaseView {
        /**
         * Display user info to the ui
         * @param user user want to display
         */
        void displayUserInfo(User user);
    }
}
