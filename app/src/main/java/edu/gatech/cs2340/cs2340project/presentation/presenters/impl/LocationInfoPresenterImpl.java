package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetails;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetLocationDetailsImpl;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LocationInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class LocationInfoPresenterImpl extends AbstractPresenter implements LocationInfoPresenter,
        GetLocationDetails.CallBack {

    private LocationInfoPresenter.View mView;
    private LocationRepository mLocationRepository;
    private Integer _key;

    public LocationInfoPresenterImpl(Integer key, Executor executor, MainThread mainThread,
                                     View view, LocationRepository locationRepository) {
        super(executor, mainThread);
        _key = key;
        mView = view;
        mLocationRepository = locationRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();

        // initialize the interactor
        GetLocationDetails interactor = new GetLocationDetailsImpl(
                _key,
                mExecutor,
                mMainThread,
                this,
                mLocationRepository
        );

        // run the interactor
        interactor.execute();
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
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onLocationRetrieved(Location location) {
        mView.hideProgress();
        mView.displayLocationInfo(location);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();

    }

}
