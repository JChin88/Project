package edu.gatech.cs2340.cs2340project.threading;

import android.os.Handler;
import android.os.Looper;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public final class MainThreadImpl implements MainThread {

    private final Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    private static class SMainThreadHolder {
        private static final MainThread sMainThread = new MainThreadImpl();
    }

    /**
     *
     * @return main thread of the ui
     */
    public static MainThread getInstance() {

        return SMainThreadHolder.sMainThread;
    }
}
