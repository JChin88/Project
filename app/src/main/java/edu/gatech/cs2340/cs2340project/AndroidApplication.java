package edu.gatech.cs2340.cs2340project;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import edu.gatech.cs2340.cs2340project.presentation.dagger.component.AppComponent;
import edu.gatech.cs2340.cs2340project.presentation.dagger.component.DaggerAppComponent;
import edu.gatech.cs2340.cs2340project.presentation.dagger.module.ApplicationModule;

public class AndroidApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
//        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


}
