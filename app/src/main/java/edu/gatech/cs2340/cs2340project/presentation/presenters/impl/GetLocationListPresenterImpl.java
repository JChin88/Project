package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationOptionsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetLocationListPresenter;

public class GetLocationListPresenterImpl implements GetLocationListPresenter{

    private GetLocationListPresenter.LocationListView locationListView;
    private GetLocationOptionsInteractor getLocationOptionsInteractor;

    @Inject
    public GetLocationListPresenterImpl(GetLocationOptionsInteractor getLocationOptionsInteractor) {
        this.getLocationOptionsInteractor = getLocationOptionsInteractor;
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

    @Override
    public void setView(LocationListView locationListView) {
        this.locationListView = locationListView;
    }

    @Override
    public void getLocationList() {
        locationListView.showProgress();
        getLocationOptionsInteractor.execute(new GetLocationListObserver(), null);
    }

    private final class GetLocationListObserver extends DefaultObserver<FirestoreRecyclerOptions<DonationLocation>> {
        @Override
        public void onNext(FirestoreRecyclerOptions<DonationLocation> options) {
            super.onNext(options);
            locationListView.hideProgress();
            locationListView.displayLocationList(options);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            locationListView.hideProgress();
        }
    }
}
