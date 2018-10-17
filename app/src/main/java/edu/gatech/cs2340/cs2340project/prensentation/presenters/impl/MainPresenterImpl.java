package edu.gatech.cs2340.cs2340project.prensentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.UserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.UserInfoInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.prensentation.presenters.MainPresenter;
import edu.gatech.cs2340.cs2340project.prensentation.presenters.base.AbstractPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        UserInfoInteractor.Callback {

    private MainPresenter.View mView;
    private UserRepository mUserRepository;
    private String _id;

    public MainPresenterImpl(String id, Executor executor, MainThread mainThread,
                             View view, UserRepository userRepository) {
        super(executor, mainThread);
        _id = id;
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();

        // initialize the interactor
        UserInfoInteractor interactor = new UserInfoInteractorImpl(
                _id,
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
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onUserRetrieved(User user) {
        mView.hideProgress();
        mView.displayUserInfo(user);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}
