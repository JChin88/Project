package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationDetailsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.Impl.GetLocationDetailsInteractorImpl;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LocationInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

/**
 * @author Hoa V Luu
 */
public class LocationInfoPresenterImpl extends AbstractPresenter implements LocationInfoPresenter,
        GetLocationDetailsInteractor.Callback {

    private final LocationInfoPresenter.View mView;
    private final LocationRepository mLocationRepository;
    private final String key;

    /**
     * Constrcutor
     * @param key key of location wanted to get info
     * @param executor background thread
     * @param mainThread main thread
     * @param view view want to display
     * @param locationRepository repository holds value
     */
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
        Interactor interactor = new GetLocationDetailsInteractorImpl(
                key,
                mExecutor,
                mMainThread,
                this,
                mLocationRepository
        );
        mLocationRepository.setInteractor(interactor);
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
