package edu.gatech.cs2340.cs2340project.data;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class JobSerice extends JobService {
    private static final String TAG  = "JobService";
    private boolean jobCancalled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
        return true;
    }

    public void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (jobCancalled) {
                    return;
                }
                Log.d(TAG, "Job Finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancalled = true;
        return false;
    }
}
