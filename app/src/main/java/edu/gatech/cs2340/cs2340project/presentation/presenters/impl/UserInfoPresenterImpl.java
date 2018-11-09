package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetUserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetUserInfoInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class UserInfoPresenterImpl extends AbstractPresenter implements UserInfoPresenter,
        GetUserInfoInteractor.Callback {

    private GetUserInfoInteractor mInteractor;
    private UserInfoPresenter.View mView;
    private UserRepository mUserRepository;
    private String id;

    public UserInfoPresenterImpl(String id, Executor threadExecutor, MainThread mainThread,
                                 View view, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.id = id;
        mView = view;
        mUserRepository = userRepository;
        mInteractor = new GetUserInfoInteractorImpl(
                id,
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
    public void onUserRetrieved(User user) {
        mView.hideProgress();
        mView.displayUserInfo(user);
    }

    @Override
    public void onRetrievalFailed(String errorMessage) {
        mView.hideProgress();
        mView.showError(errorMessage);
    }
}
