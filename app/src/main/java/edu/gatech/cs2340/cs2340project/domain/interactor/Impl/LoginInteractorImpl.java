package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

import static edu.gatech.cs2340.cs2340project.data.UserDataRepository.LOGIN_SUCCESS;

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    LoginInteractor.Callback mCallback;
    UserRepository mUserRepository;
    String userId;
    String userPassword;
    String loginMessage;

    public LoginInteractorImpl(String id, String password, Executor threadExecutor,
                               MainThread mainThread, Callback callback,
                               UserRepository userRepository) {
        super(threadExecutor, mainThread);
        userId = id;
        userPassword = password;
        mCallback = callback;
        mUserRepository = userRepository;
    }

    private void notifyError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginFailed(errorMessage);
            }
        });
    }

    private void moveToUserInfo(final User user) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginSuccess(user);
            }
        });
    }

    @Override
    public void run() {
        //String loginMessage;
        loginMessage = mUserRepository.login(userId, userPassword);

        User user1;
        if (loginMessage == null) {
            loginMessage = "Login message is null";
        }

        if (!(loginMessage.equals(LOGIN_SUCCESS))) {
            notifyError(loginMessage);
            return;
        }
        String currentUID = mUserRepository.getCurrentUID();
        User user = mUserRepository.getUser(currentUID);

        moveToUserInfo(user);
    }

}