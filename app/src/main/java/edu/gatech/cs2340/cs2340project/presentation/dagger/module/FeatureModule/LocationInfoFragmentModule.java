package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LocationInfoPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LocationInfoPresenterImpl;

@Module
public abstract class LocationInfoFragmentModule {

    @FragmentScoped
    @Binds
    abstract LocationInfoPresenter provideLocationInfoPresenter(LocationInfoPresenterImpl locationInfoPresenter);
}
