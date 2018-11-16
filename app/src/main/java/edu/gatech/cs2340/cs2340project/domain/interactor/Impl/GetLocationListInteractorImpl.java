package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;

/**
 * @author Hoa V Luu
 */
public class GetLocationListInteractorImpl extends AbstractInteractor
        implements GetLocationListInteractor {

    private final GetLocationListInteractorImpl.Callback mCallback;
    private final LocationRepository mLocationRepository;

    /**
     * Constructor
     * @param threadExecutor background thread
     * @param mainThread main thread
     * @param mCallback get location callback
     * @param mLocationRepository repository stored data
     */
    public GetLocationListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         Callback mCallback,
                                         LocationRepository mLocationRepository) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mLocationRepository = mLocationRepository;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLocationListRetrievedFail();
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLocationListRetrieved();
            }
        });
    }

    @Override
    public void run() {
        mLocationRepository.getLocationList();
    }
}
