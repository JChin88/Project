package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetLocationListPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.GetLocationListPresenterImpl;

@Module
public abstract class HomeFragmentModule {

    @FragmentScoped
    @Binds
    abstract GetLocationListPresenter provideGetLocationListPresenter
            (GetLocationListPresenterImpl getLocationListPresenter);
}
