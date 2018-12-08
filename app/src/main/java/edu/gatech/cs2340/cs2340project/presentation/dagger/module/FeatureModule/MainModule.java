package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.ActivityScoped;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.DonationItemDetailsFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.HomeFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.LocationInfoFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.LocationInventoryFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.MapFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.SearchFragment;

@Module
public abstract class MainModule {

    @Provides
    @Nullable
    static User provideUser(UserRepository userRepository) {
        return userRepository.getUser();
    }

    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
     abstract HomeFragment provideHomeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MapFragment provideMapFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SearchFragment provideSearchFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = LocationInfoFragmentModule.class)
    abstract LocationInfoFragment provideLocationInfoFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = LocationInventoryFragmentModule.class)
    abstract LocationInventoryFragment provideLocationInventoryFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = DonationItemDetailsFragmentModule.class)
    abstract DonationItemDetailsFragment provideDonationItemDetailsFragment();


}
