package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.ActivityScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LoginPresenterImpl;

@Module
public abstract class LoginModule {

    @ActivityScoped
    @Binds
    public abstract LoginPresenter provideLoginPresenter(LoginPresenterImpl presenter);
}
