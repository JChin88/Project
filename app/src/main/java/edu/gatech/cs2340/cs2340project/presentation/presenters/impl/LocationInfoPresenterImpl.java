package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetLocationDetailsInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LocationInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class LocationInfoPresenterImpl extends AbstractPresenter implements LocationInfoPresenter,
        GetLocationDetailsInteractor.Callback {

    private LocationInfoPresenter.View mView;
    private LocationRepository mLocationRepository;
    private String key;

    public LocationInfoPresenterImpl(String key, Executor executor, MainThread mainThread,
                                     View view, LocationRepository locationRepository) {
        super(executor, mainThread);
        this.key = key;
        mView = view;
        mLocationRepository = locationRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();

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
    public void onLocationRetrieved(Location location) {
        mView.hideProgress();
        mView.displayLocationInfo(location);
    }

    @Override
    public void onRetrievalFailed(String errorMessage) {
        mView.hideProgress();
        mView.showError(errorMessage);
    }

}
