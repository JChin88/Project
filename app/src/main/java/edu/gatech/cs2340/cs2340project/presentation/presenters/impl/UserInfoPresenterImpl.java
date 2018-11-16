package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetUserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetUserInfoInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.UserInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

/**
 * @author Hoa V Luu
 */
public class UserInfoPresenterImpl extends AbstractPresenter implements UserInfoPresenter,
        GetUserInfoInteractor.Callback {

    private final GetUserInfoInteractor mInteractor;
    private final UserInfoPresenter.View mView;

    /**
     * constructor
     * @param id id of user info wanted to display
     * @param threadExecutor background thread
     * @param mainThread main thread
     * @param view view want to display
     * @param userRepository repository hold user info
     */
    public UserInfoPresenterImpl(String id, Executor threadExecutor, MainThread mainThread,
                                 View view, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        mView = view;
        mInteractor = new GetUserInfoInteractorImpl(
                id,
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
