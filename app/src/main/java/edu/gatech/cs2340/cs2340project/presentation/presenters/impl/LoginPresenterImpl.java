package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
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

    private LoginInteractor mInteractor;
    private LoginPresenter.LoginView mView;
    private UserRepository mUserRepository;
    private String mUserId;
    private String mUserPassword;

    public LoginPresenterImpl(String id, String password, Executor executor, MainThread mainThread,
                                 LoginView view, UserRepository userRepository) {
        super(executor, mainThread);
        mUserId = id;
        mUserPassword = password;
        mView = view;
        mUserRepository = userRepository;
        mInteractor = new LoginInteractorImpl(
                mUserId,
                mUserPassword,
                mExecutor,
                mMainThread,
                this,
                mUserRepository
        );
        mUserRepository.setInteractor(mInteractor);
    }

    @Override
    public void resume() {
        mView.showProgress();
        mView.hideRetry();
        // run the interactor
        mInteractor.execute();
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
        mView.showRetry();
        mView.moveToUserHomeActivity(userID);
    }

    @Override
    public void onLoginFailed(String errorMessage) {
        mView.hideProgress();
        mView.showRetry();
        onError(errorMessage);
    }
}
