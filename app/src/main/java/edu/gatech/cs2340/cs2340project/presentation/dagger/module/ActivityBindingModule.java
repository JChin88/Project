package edu.gatech.cs2340.cs2340project.presentation.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule.LoginModule;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule.MainModule;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule.RegisterUserModule;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.ActivityScoped;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.AddEditDonationItemActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.LoginActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.MainActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.RegisterUserActivity;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.UpdateNewUserRights;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract UpdateNewUserRights updateNewUserRights();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RegisterUserModule.class)
    abstract RegisterUserActivity registerUserActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract AddEditDonationItemActivity addDonationItemActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
