package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.JobExecutor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.interactor.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.LoginInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.Login;
import edu.gatech.cs2340.cs2340project.domain.interactor.LoginInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.dagger.PerActivity;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class LoginPresenterImpl implements LoginPresenter{

    private LoginInteractor mInteractor;
    private LoginPresenter.LoginView mView;
    private UserRepository mUserRepository;

    private final Login loginInteractor;

//    @Inject
//    public LoginPresenterImpl(@Nullable String id, @Nullable String password,
//                              Executor executor, MainThread mainThread,
//                              LoginView view, UserRepository userRepository) {
//        super(executor, mainThread);
//        mUserId = id;
//        mUserPassword = password;
//        mView = view;
//        mUserRepository = userRepository;
//        mInteractor = new LoginInteractorImpl(
//                mUserId,
//                mUserPassword,
//                mExecutor,
//                mMainThread,
//                this,
//                mUserRepository
//        );
//        mUserRepository.setInteractor(mInteractor);
//    }

    @Inject
    public LoginPresenterImpl(Login loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void setView(@NonNull LoginView view) {
        mView = view;
    }


    @Override
    public void initialize(String email, String password) {
        mView.showProgress();
        mView.hideViewRetry();
        // run the interactor
////        mInteractor.execute();
//        ThreadExecutor threadExecutor = new JobExecutor();
//        Login loginInteractor = new Login(threadExecutor, mMainThread, mUserRepository);
        loginInteractor.execute(new LoginObserver(), Login.Params.forUser(email, password));
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
    public void resume() {

    }

private final class LoginObserver extends DefaultObserver<String> {

        @Override public void onComplete() {

        }

        @Override public void onError(Throwable e) {
            String errorMessage = e.getMessage();
            mView.hideProgress();
            mView.showViewRetry();
            mView.showError(errorMessage);
        }

        @Override public void onNext(String userID) {
            mView.hideProgress();
            mView.showViewRetry();
            mView.moveToUserHomeActivity(userID);
        }
    }
}
