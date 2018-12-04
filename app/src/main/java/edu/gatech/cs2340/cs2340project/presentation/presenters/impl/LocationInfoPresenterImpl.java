package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LocationInfoPresenter;

public class LocationInfoPresenterImpl implements LocationInfoPresenter{

    private LocationInfoPresenter.LocationInfoView mView;

    private final GetLocationDetailsInteractor getLocationDetailsInteractor;

    @Inject
    public LocationInfoPresenterImpl(GetLocationDetailsInteractor getLocationDetailsInteractor) {
        this.getLocationDetailsInteractor = getLocationDetailsInteractor;
    }

    @Override
    public void setView(LocationInfoView locationInfoView) {
        mView = locationInfoView;
    }

    @Override
    public void initialize(String key) {
        mView.showProgress();
        getLocationDetailsInteractor.execute(new LocationInfoObserver(),
                GetLocationDetailsInteractor.Params.forUser(key));
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

    private final class LocationInfoObserver extends DefaultObserver<DonationLocation> {

        @Override
        public void onComplete() {
            mView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            String errorMessage = e.getMessage();
            mView.hideProgress();
            mView.showError(errorMessage);
        }

        @Override
        public void onNext(DonationLocation location) {
            mView.displayLocationInfo(location);
        }
    }
}
