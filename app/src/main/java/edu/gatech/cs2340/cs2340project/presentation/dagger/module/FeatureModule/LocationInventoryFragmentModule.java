package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemFSOptionsPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.GetDonationItemFSOptionsPresenterImpl;

@Module
public abstract class LocationInventoryFragmentModule {

    @FragmentScoped
    @Binds
    abstract GetDonationItemFSOptionsPresenter
    provideGetDonationItemQueryPresenter(GetDonationItemFSOptionsPresenterImpl
                                                 getDonationItemQueryPresenter);
}
