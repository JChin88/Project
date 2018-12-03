package edu.gatech.cs2340.cs2340project.presentation.dagger.module.FeatureModule;

import dagger.Binds;
import dagger.Module;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.Scoped.ActivityScoped;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.AddUserPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.AddUserPresenterImpl;

@Module
public abstract class RegisterUserModule {

    @ActivityScoped
    @Binds
    abstract AddUserPresenter provideAddUserPresenter(AddUserPresenterImpl addUserPresenter);

}
