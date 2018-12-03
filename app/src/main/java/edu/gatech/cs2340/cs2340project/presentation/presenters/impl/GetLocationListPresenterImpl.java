package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetLocationListPresenter;

public class GetLocationListPresenterImpl implements GetLocationListPresenter{

    GetLocationListPresenter.LocationListView locationListView;
    GetLocationListInteractor getLocationListInteractor;

    @Inject
    public GetLocationListPresenterImpl(GetLocationListInteractor getLocationListInteractor) {
        this.getLocationListInteractor = getLocationListInteractor;
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
    }
}
