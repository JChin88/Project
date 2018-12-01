package edu.gatech.cs2340.cs2340project.presentation.dagger.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.LoginActivity;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();
}
