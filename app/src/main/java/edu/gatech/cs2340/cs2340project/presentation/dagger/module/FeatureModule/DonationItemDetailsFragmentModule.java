package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.GetDonationItemPresenterImpl;

@Module
public abstract class DonationItemDetailsFragmentModule {

    @FragmentScoped
    @Binds
    abstract GetDonationItemPresenter
    provideGetDonationItemPresenter(GetDonationItemPresenterImpl getDonationItemPresenter);
}
