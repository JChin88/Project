package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    public static final String LOGIN_SUCCESS = "Login Success!";
    public static final String LOGIN_INVALID_UIDPS = "Incorrect email or password!";

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
//        mUserRepository.login(userId, userPassword)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            onNext(m);
//                            LOGIN_MESSAGE = LOGIN_SUCCESS;
//                            interactor.onNext(mAuth.getCurrentUser().getUid());
//                        } else {
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException
//                                    || task.getException() instanceof FirebaseAuthInvalidUserException) {
//                                LOGIN_MESSAGE = LOGIN_INVALID_UIDPS;
//                            } else {
//                                LOGIN_MESSAGE = task.getException().getMessage();
//                            }
//                            interactor.onError(LOGIN_MESSAGE);
//                        }
//                    }
//                });
    }

}