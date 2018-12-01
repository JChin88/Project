package edu.gatech.cs2340.cs2340project.presentation.view.activities;

import android.app.Activity;
import android.os.Bundle;

import edu.gatech.cs2340.cs2340project.AndroidApplication;
import edu.gatech.cs2340.cs2340project.presentation.dagger.component.AppComponent;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends Activity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.getApplicationComponent().inject(this);
//    }

//    /**
//     * Get the Main Application component for dependency injection.
//     *
//     * @return {@link AppComponent}
//     */
//    protected AppComponent getApplicationComponent() {
//        return ((AndroidApplication) getApplication()).getApplicationComponent();
//    }
//
//    /**
//     * Get an Activity module for dependency injection.
//     *
//     * @return {e}
//     */
//    protected ActivityModule getActivityModule() {
//        return new ActivityModule(this);
//    }
}
