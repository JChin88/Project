package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetUserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class GetUserInfoInteractorImpl extends AbstractInteractor implements GetUserInfoInteractor {

    GetUserInfoInteractor.Callback mCallBack;
    UserRepository mUserRepository;
    String userID;

    @Inject
    public GetUserInfoInteractorImpl(String id, Executor threadExecutor, MainThread mainThread
                                    , Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        userID = id;
        mCallBack = callback;
        mUserRepository = userRepository;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onRetrievalFailed(errorMessage);
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onUserRetrieved((User) params);
            }
        });
    }

    @Override
    public void run() {
        mUserRepository.getUser(userID);
//        // retrieve the user
//        final User user = mUserRepository.getUser(_id);
//
//        // check if we have failed to retrieve our user
//        if (user == null) {
//
//            // notify the failure on the main thread
//            onError("No user to display :(");
//
//            return;
//        }
//
//        // we have retrieved our user, notify the UI on the main thread
//        goBackMainThread(user);
    }
}
