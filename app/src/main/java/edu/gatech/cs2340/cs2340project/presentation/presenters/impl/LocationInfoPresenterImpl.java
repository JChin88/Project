package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
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

<<<<<<< HEAD
        // initialize the interactor
        GetLocationDetailsInteractor interactor = new GetLocationDetailsInteractorImpl(
                key,
                mExecutor,
                mMainThread,
                this,
                mLocationRepository
        );

        // run the interactor
        interactor.execute();
=======
    @Override
    public void resume() {
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f
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

    private final class LocationInfoObserver extends DefaultObserver<Location> {

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
        public void onNext(Location location) {
            mView.displayLocationInfo(location);
        }
    }
}
