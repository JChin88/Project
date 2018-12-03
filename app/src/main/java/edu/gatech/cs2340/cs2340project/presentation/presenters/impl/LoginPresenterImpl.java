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
    public void login(String email, String password) {
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
    }
}
