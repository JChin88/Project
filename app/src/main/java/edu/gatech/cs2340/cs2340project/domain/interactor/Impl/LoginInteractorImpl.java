package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

/**
 * @author Hoa V Luu
 */
public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private final LoginInteractor.Callback mCallback;
    private final UserRepository mUserRepository;
    private final String userId;
    private final String userPassword;

    /**
     * Constructor
     * @param id user id want to login
     * @param password user password want to login
     * @param threadExecutor background thread
     * @param mainThread main thread
     * @param callback callback to use
     * @param userRepository user repository hold user data
     */
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