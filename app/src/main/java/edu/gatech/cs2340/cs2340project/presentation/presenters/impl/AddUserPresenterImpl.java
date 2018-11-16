package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.AddUserInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.AddUserInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.AddUserPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

/**
 * Add user presenter implementation version
 */
public class AddUserPresenterImpl extends AbstractPresenter implements AddUserPresenter,
        AddUserInteractor.Callback {


    private final Interactor mInteractor;
    private final AddUserPresenter.RegisterView mView;

    /**
     * Constructor for the presenter
     * @param executor background thread
     * @param mainThread main thread of ui
     * @param mView callback of use case
     * @param mUserRepository the user repository
     * @param userName user name
     * @param userEmail user email
     * @param userPassword user password
     * @param userRights user rights
     */
    public AddUserPresenterImpl(Executor executor, MainThread mainThread, RegisterView mView,
                                UserRepository mUserRepository, String userName, String userEmail,
                                String userPassword, UserRights userRights) {
        super(executor, mainThread);
        this.mView = mView;
        mInteractor = new AddUserInteractorImpl(executor, mainThread, this, mUserRepository,
                userName, userEmail, userPassword, userRights);
        mUserRepository.setInteractor(mInteractor);
    }

    @Override
    public void resume() {
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
    public void onAddUser(String successMessage) {
        mView.hideProgress();
        mView.showViewRetry();
        mView.showSuccessMessage(successMessage);
        mView.moveToLogin();
    }

    @Override
    public void onAddUserFailed(String errorMessage) {
        mView.hideProgress();
        mView.showViewRetry();
        mView.showError(errorMessage);
    }
}
