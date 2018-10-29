package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import android.widget.Toast;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.AddUserInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.AddUserInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.AddUserPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class AddUserPresenterImpl extends AbstractPresenter implements AddUserPresenter,
        AddUserInteractor.Callback {


    private Interactor mInteractor;
    private AddUserPresenter.RegisterView mView;
    private UserRepository mUserRepository;
    private String userName;
    private String userEmail;
    private String userPassword;
    private User.AccountType userType;

    public AddUserPresenterImpl(Executor executor, MainThread mainThread, RegisterView mView,
                                UserRepository mUserRepository, String userName, String userEmail,
                                String userPassword, User.AccountType userType) {
        super(executor, mainThread);
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
        mInteractor = new AddUserInteractorImpl(executor, mainThread, this, mUserRepository,
                userName, userEmail, userPassword, userType);
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
