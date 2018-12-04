package edu.gatech.cs2340.cs2340project.presentation.presenters.contracts;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.BasePresenter;
import edu.gatech.cs2340.cs2340project.presentation.view.BaseView;

public interface UserInfoPresenter extends BasePresenter {

    void getUser(String id);

    void setView(UserInfoView userInfoView);

    interface UserInfoView extends BaseView {
        void displayUserInfo(User user);
    }
}
