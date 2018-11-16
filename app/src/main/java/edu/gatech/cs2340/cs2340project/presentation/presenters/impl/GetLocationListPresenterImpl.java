package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetLocationListInteractor;
import edu.gatech.cs2340.cs2340project.presentation.presenters.GetLocationListPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.base.AbstractPresenter;

/**
 * @author Hoa V Luu
 */
public class GetLocationListPresenterImpl extends AbstractPresenter
        implements GetLocationListPresenter,
        GetLocationListInteractor.Callback {

    /**
     * Presenter constructor
     * @param executor background thread
     * @param mainThread main thread
     */
    public GetLocationListPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
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
    public void onLocationListRetrieved() {

    }

    @Override
    public void onLocationListRetrievedFail() {

    }
}
