package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    LoginInteractor.Callback mCallback;
    UserRepository mUserRepository;
    String userId;
    String userPassword;

    public LoginInteractorImpl(String id, String password, Executor threadExecutor,
                               MainThread mainThread, Callback callback,
                               UserRepository userRepository) {
        super(threadExecutor, mainThread);
        userId = id;
        userPassword = password;
        mCallback = callback;
        mUserRepository = userRepository;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginFailed(errorMessage);
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginSuccess((String) params);
            }
        });
    }

    @Override
    public void run() {
        mUserRepository.login(userId, userPassword);
    }

}