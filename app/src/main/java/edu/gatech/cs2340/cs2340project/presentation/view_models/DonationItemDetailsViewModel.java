package edu.gatech.cs2340.cs2340project.presentation.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetDonationItemInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class DonationItemDetailsViewModel extends ViewModel {

    private final GetDonationItemInteractor getDonationItemInteractor;

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public DonationItemDetailsViewModel(GetDonationItemInteractor getDonationItemInteractor) {
        this.getDonationItemInteractor = getDonationItemInteractor;
    }

    public void clean() {
        getDonationItemInteractor.dispose();
    }

    public void getDonationItem(String key) {
        response.setValue(Response.loading());
        getDonationItemInteractor.execute(new GetDonationItemDetailsObserver(),
                GetDonationItemInteractor.Params.getDonationItem(key));
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private final class GetDonationItemDetailsObserver extends DefaultObserver<DonationItem> {
        @Override
        public void onNext(DonationItem donationItem) {
            response.setValue(Response.success(donationItem));
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
