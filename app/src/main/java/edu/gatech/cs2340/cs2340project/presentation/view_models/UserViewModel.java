package edu.gatech.cs2340.cs2340project.presentation.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.AddUserWURInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetCurrentUserInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class UserViewModel extends ViewModel {

    private final AddUserWURInteractor addUserWURInteractor;

    private GetCurrentUserInteractor getCurrentUserInteractor;

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public UserViewModel(AddUserWURInteractor addUserWURInteractor,
                         GetCurrentUserInteractor getCurrentUserInteractor) {
        this.addUserWURInteractor = addUserWURInteractor;
        this.getCurrentUserInteractor = getCurrentUserInteractor;
    }

    public void clean() {
        addUserWURInteractor.dispose();
    }

    public void addUSerWUR(UserRights userRights) {
        response.setValue(Response.loading());
        addUserWURInteractor.execute(new AddUserWURObsever(),
                AddUserWURInteractor.Params.addUserWUR(userRights));
    }

    public void getCurrentUser(){
        response.setValue(Response.loading());
        getCurrentUserInteractor.execute(new GetCurrentUserObserver(), null);
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private final class AddUserWURObsever extends DefaultObserver<String> {
        @Override
        public void onNext(String successMessage) {
            response.setValue(Response.success(successMessage));
        }

        @Override
        public void onError(Throwable e) {
            response.setValue(Response.error(e));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private final class GetCurrentUserObserver extends DefaultObserver<User> {
        @Override
        public void onNext(User currentUser) {
            response.setValue(Response.success(currentUser));
        }

        @Override
        public void onError(Throwable e) {
            response.setValue(Response.error(e));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
