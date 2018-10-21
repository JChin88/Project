package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetUserInfo;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetUserInfoImpl;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class UserInfoPresenterImpl extends AbstractPresenter implements UserInfoPresenter,
        GetUserInfo.CallBack {

    private UserInfoPresenter.View mView;
    private UserRepository mUserRepository;
    private String _id;

    public UserInfoPresenterImpl(String id, Executor threadExecutor, MainThread mainThread,
                                 View view, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        _id = id;
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();

        // initialize the interactor
        GetUserInfo interactor = new GetUserInfoImpl(
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
    public void onError(String errorMessage) {
        mView.showError(errorMessage);
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
