package edu.gatech.cs2340.cs2340project.threading;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
@Singleton
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    @Inject
    MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
