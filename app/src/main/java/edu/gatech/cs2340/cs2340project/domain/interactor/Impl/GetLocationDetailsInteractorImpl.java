package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;

public class GetLocationDetailsInteractorImpl extends AbstractInteractor implements GetLocationDetailsInteractor {

    private GetLocationDetailsInteractor.Callback mCallBack;
    private LocationRepository mLocationRepository;
    private String key;

    public GetLocationDetailsInteractorImpl(String key, Executor threadExecutor, MainThread mainThread
            , Callback callback, LocationRepository locationRepository) {
        super(threadExecutor, mainThread);
        this.key = key;
        mCallBack = callback;
        mLocationRepository = locationRepository;
    }

    @Override
    public void onError(final String string) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onRetrievalFailed(string);
            }
        });
    }

    @Override
    public void onNext(final Object location) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallBack.onLocationRetrieved((Location) location);
            }
        });
    }

    @Override
    public void run() {
            mLocationRepository.getLocation(key);
//        // retrieve the user
//        final Location location = mLocationRepository.getLocation(_key);
//
//        // check if we have failed to retrieve our user
//        if (location == null) {
//
//            // notify the failure on the main thread
//            notifyError("No location to display :(");
//            return;
//        }
//
//        // we have retrieved our user, notify the UI on the main thread
//        goBackMainThread(location);

    }
}
