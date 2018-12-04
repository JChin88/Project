package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetUserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.UserInfoPresenter;

public class UserInfoPresenterImpl implements UserInfoPresenter{

    private GetUserInfoInteractor getUserInfoInteractor;
    private UserInfoPresenter.UserInfoView userInfoView;

    @Inject
    public UserInfoPresenterImpl(GetUserInfoInteractor getUserInfoInteractor) {
        this.getUserInfoInteractor = getUserInfoInteractor;
    }

    @Override
    public void setView(UserInfoView userInfoView) {
        this.userInfoView = userInfoView;
    }

    @Override
    public void getUser(String id) {
        getUserInfoInteractor.execute(new UserInfoObserver(), GetUserInfoInteractor.Params.forUser(id));
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    private final class UserInfoObserver extends DefaultObserver<User> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {
            String errorMessage = e.getMessage();
            userInfoView.hideProgress();
            userInfoView.showError(errorMessage);
        }

        @Override
        public void onNext(User user) {
            userInfoView.hideProgress();
            userInfoView.displayUserInfo(user);
        }
    }
}
