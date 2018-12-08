package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginPresenter.LoginView mView;
    private final LoginInteractor loginInteractor;

    @Inject
    public LoginPresenterImpl(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void setView(@NonNull LoginView view) {
        mView = view;
    }

    @Override
<<<<<<< HEAD
    public void resume() {
=======
    public void login(String email, String password) {
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
        mView.showProgress();
        mView.hideViewRetry();
        // run the interactor
        loginInteractor.execute(new LoginObserver(), LoginInteractor.Params.login(email, password));
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
        this.loginInteractor.dispose();
        this.mView = null;
    }

    @Override
<<<<<<< HEAD
    public void onLoginSuccess(String userID) {
        mView.hideProgress();
        mView.showViewRetry();
        mView.moveToUserHomeActivity(userID);
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        mView.hideProgress();
        mView.showViewRetry();
        mView.showError(errorMessage);
=======
    public void resume() {
    }

    private final class LoginObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {
            String errorMessage = e.getMessage();
            mView.hideProgress();
            mView.showViewRetry();
            mView.showError(errorMessage);
        }

        @Override
        public void onNext(String userID) {
            mView.hideProgress();
            mView.showViewRetry();
            mView.moveToUserHomeActivity(userID);
        }
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
    }
}
