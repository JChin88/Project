package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.AddUserInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.ActivityScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.AddUserPresenter;

public class AddUserPresenterImpl implements AddUserPresenter {

    private AddUserInteractor addUserInteractor;
    private AddUserPresenter.RegisterView registerView;

    @Inject
    public AddUserPresenterImpl(AddUserInteractor addUserInteractor) {
        this.addUserInteractor = addUserInteractor;
    }

    @Override
    public void addUser(String name, String email, String password, UserRights userRights) {
        addUserInteractor.execute(new AddUserObserver(),
                AddUserInteractor.Params.addUser(name, email, password, userRights));
    }

    @Override
    public void setView(RegisterView registerView) {
     this.registerView = registerView;
    }

    @Override
    public void resume() {
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

    private final class AddUserObserver extends DefaultObserver<String> {

        @Override
        public void onNext(String successMessage) {
            registerView.hideProgress();
            registerView.showViewRetry();
            registerView.showSuccessMessage(successMessage);
            registerView.moveToLogin();
        }

        @Override
        public void onError(Throwable e) {
            registerView.hideProgress();
            registerView.showViewRetry();
            registerView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            registerView.hideProgress();
        }
    }
}
