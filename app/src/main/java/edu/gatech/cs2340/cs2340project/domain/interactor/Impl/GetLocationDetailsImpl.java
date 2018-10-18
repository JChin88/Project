package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetails;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;

public class GetLocationDetailsImpl extends AbstractInteractor implements GetLocationDetails {

    edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetails.CallBack mCallBack;
    LocationRepository mLocationRepository;
    Integer _key;

    public GetLocationDetailsImpl(Integer key, Executor threadExecutor, MainThread mainThread
            , CallBack callback, LocationRepository locationRepository) {
        super(threadExecutor, mainThread);
        _key = key;
        mCallBack = callback;
        mLocationRepository = locationRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onRetrievalFailed("No location to display :(");
            }
        });
    }

    private void postLocationInfo(final Location location) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onLocationRetrieved(location);
            }
        });
    }

    @Override
    public void run() {

        // retrieve the user
        final Location location = mLocationRepository.getLocation(_key);

        // check if we have failed to retrieve our user
        if (location == null) {

            // notify the failure on the main thread
            notifyError();
            return;
        }

        // we have retrieved our user, notify the UI on the main thread
        postLocationInfo(location);

    }
}
