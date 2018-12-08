package edu.gatech.cs2340.cs2340project.presentation.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationOptionsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class DonationLocationViewModel extends ViewModel {

    private final GetLocationOptionsInteractor getLocationOptionsInteractor;

    private GetLocationListInteractor getLocationListInteractor;

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public DonationLocationViewModel(GetLocationOptionsInteractor getLocationOptionsInteractor,
                                     GetLocationListInteractor getLocationListInteractor) {
        this.getLocationOptionsInteractor = getLocationOptionsInteractor;
        this.getLocationListInteractor = getLocationListInteractor;
    }

    public void clean() {
        getLocationOptionsInteractor.dispose();
        getLocationListInteractor.dispose();
    }

    public void getLocationOptions() {
        response.setValue(Response.loading());
        getLocationOptionsInteractor.execute(new GetLocationOptionsObserver(), null);
    }

    public void getLocationList() {
        response.setValue(Response.loading());
        getLocationListInteractor.execute(new GetLocationListObserver(), null);
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private final class GetLocationOptionsObserver extends DefaultObserver<FirestoreRecyclerOptions<DonationLocation>> {
        @Override
        public void onNext(FirestoreRecyclerOptions<DonationLocation> options) {
            response.setValue(Response.success(options));
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

    private final class GetLocationListObserver extends DefaultObserver<List<DonationLocation>> {
        @Override
        public void onNext(List<DonationLocation> donationLocationList) {
            response.setValue(Response.success(donationLocationList));
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
