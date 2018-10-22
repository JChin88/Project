package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.LoginInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter,
        LoginInteractor.Callback {

    private LoginPresenter.View mView;
    private UserRepository mUserRepository;
    private String mUserId;
    private String mUserPassword;

    public LoginPresenterImpl(String id, String password, Executor executor, MainThread mainThread,
                                 View view, UserRepository userRepository) {
        super(executor, mainThread);
        mUserId = id;
        mUserPassword = password;
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {
        mView.showProgress();
        // initialize the interactor
        LoginInteractor interactor = new LoginInteractorImpl(
                mUserId,
                mUserPassword,
                mExecutor,
                mMainThread,
                this,
                mUserRepository
        );
        // run the interactor
        interactor.execute();
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

    @Override
    public void onError(String errorMessage) {
        mView.showError(errorMessage);
    }

    @Override
    public void onLoginSuccess(String userID) {
        mView.hideProgress();
        mView.moveToUserHomeActivity(userID);
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        mView.hideProgress();
        onError(errorMessage);
    }
}
