package edu.gatech.cs2340.cs2340project.presentation.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class LocationInfoViewModel extends ViewModel {
    private GetLocationDetailsInteractor getLocationDetailsInteractor;

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public LocationInfoViewModel(GetLocationDetailsInteractor getLocationDetailsInteractor) {
        this.getLocationDetailsInteractor = getLocationDetailsInteractor;
    }

    public void clean() {
        getLocationDetailsInteractor.dispose();
    }

    public void getLocationInfo(String key) {
        response.setValue(Response.loading());
        getLocationDetailsInteractor.execute(new LocationInfoObserver(),
                GetLocationDetailsInteractor.Params.forUser(key));
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private final class LocationInfoObserver extends DefaultObserver<DonationLocation> {
        @Override
        public void onNext(DonationLocation donationLocation) {
            response.setValue(Response.success(donationLocation));
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
