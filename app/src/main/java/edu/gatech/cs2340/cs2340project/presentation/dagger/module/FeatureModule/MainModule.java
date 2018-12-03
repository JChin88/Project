package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.FragmentScoped;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.HomeFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.LocationInfoFragment;
import edu.gatech.cs2340.cs2340project.presentation.view.fragments.LocationInventoryFragment;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
     abstract HomeFragment provideHomeFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = LocationInfoFragmentModule.class)
    abstract LocationInfoFragment provideLocationInfoFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = LocationInventoryFragmentModule.class)
    abstract LocationInventoryFragment provideLocationInventoryFragment();

}
