package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.GetLocationListPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

public class GetLocationListPresenterImpl extends AbstractPresenter implements GetLocationListPresenter,
        GetLocationListInteractor.Callback {

    private GetLocationListPresenter.LocationListView mView;
    private LocationRepository mLocationRepository;

    public GetLocationListPresenterImpl(Executor executor, MainThread mainThread,
                                        LocationListView mView, LocationRepository mLocationRepository) {
        super(executor, mainThread);
        this.mView = mView;
        this.mLocationRepository = mLocationRepository;
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
    public void onLocationListRetrieved(List<Location> locationList) {

    }

    @Override
    public void onLocationListRetrievedFail(String errorMessage) {

    }
}
