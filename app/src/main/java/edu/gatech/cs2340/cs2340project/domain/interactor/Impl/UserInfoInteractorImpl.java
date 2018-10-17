package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.UserInfoInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;

public class UserInfoInteractorImpl extends AbstractInteractor implements UserInfoInteractor {

    UserInfoInteractor.Callback mCallBack;
    UserRepository mUserRepository;
    String _id;

    public UserInfoInteractorImpl (String id, Executor threadExecutor, MainThread mainThread
                                    , Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        _id = id;
        mCallBack = callback;
        mUserRepository = userRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onRetrievalFailed("No user to display :(");
            }
        });
    }

    private void postUserInfo(final User user) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onUserRetrieved(user);
            }
        });
    }

    @Override
    public void run() {

        // retrieve the user
        final User user = mUserRepository.getUser(_id);

        // check if we have failed to retrieve our user
        if (user == null) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our user, notify the UI on the main thread
        postUserInfo(user);

    }
}
