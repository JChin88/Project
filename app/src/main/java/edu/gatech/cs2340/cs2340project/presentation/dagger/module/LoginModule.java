package edu.gatech.cs2340.cs2340project.presentation.dagger.module;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.gatech.cs2340.cs2340project.presentation.presenters.LoginPresenter;
import edu.gatech.cs2340.cs2340project.presentation.presenters.impl.LoginPresenterImpl;

@Module
public abstract class LoginModule {

//    @FragmentScoped
//    @ContributesAndroidInjector
//    abstract LoginFragment addEditTaskFragment();

    @ActivityScoped
    @Binds
    public abstract LoginPresenter provideLoginPresenter(LoginPresenterImpl presenter);
}
