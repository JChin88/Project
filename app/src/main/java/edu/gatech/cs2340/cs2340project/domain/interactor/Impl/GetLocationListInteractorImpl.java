package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;

public class GetLocationListInteractorImpl extends AbstractInteractor implements GetLocationListInteractor {

    private GetLocationListInteractorImpl.Callback mCallback;
    private LocationRepository mLocationRepository;

    public GetLocationListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         Callback mCallback, LocationRepository mLocationRepository) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mLocationRepository = mLocationRepository;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLocationListRetrievedFail(errorMessage);
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLocationListRetrieved((List<Location>) params);
            }
        });
    }

    @Override
    public void run() {
        mLocationRepository.getLocationList();
    }
}
