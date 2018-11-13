package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.AddUserInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class AddUserInteractorImpl extends AbstractInteractor implements AddUserInteractor{

    private final AddUserInteractor.Callback mCallBack;
    private final UserRepository mUserRepository;
    private final String userName;
    private final String userEmail;
    private final String userPassword;
    private final UserRights userRights;

    public AddUserInteractorImpl (Executor threadExecutor, MainThread mainThread, Callback callback,
                                  UserRepository userRepository, String userName, String userEmail,
                                  String userPassword, UserRights userRights) {
        super(threadExecutor, mainThread);
        mCallBack = callback;
        mUserRepository = userRepository;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRights = userRights;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onAddUserFailed(errorMessage);
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onAddUser((String) params);
            }
        });
    }

    @Override
    public void run() {
        mUserRepository.addUser(userName, userEmail, userPassword, userRights);
    }
}
