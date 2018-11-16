package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.LoginInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

/**
 * @author Hoa V Luu
 */
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter,
        LoginInteractor.Callback {

    private final LoginInteractor mInteractor;
    private final LoginPresenter.LoginView mView;

    /**
     * constructor
     * @param id user id
     * @param password user password
     * @param executor background thread
     * @param mainThread main thread
     * @param view view want to show
     * @param userRepository user repository to get value
     */
    public LoginPresenterImpl(String id, String password, Executor executor, MainThread mainThread,
                                 LoginView view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mInteractor = new LoginInteractorImpl(
                id,
                password,
                mExecutor,
                mMainThread,
                this,
                userRepository
        );
        userRepository.setInteractor(mInteractor);
    }

    @Override
    public void resume() {
        mView.showProgress();
        mView.hideViewRetry();
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
    }
}
