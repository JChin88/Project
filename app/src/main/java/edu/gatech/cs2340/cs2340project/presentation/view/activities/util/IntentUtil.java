package edu.gatech.cs2340.cs2340project.presentation.view.activities.util;

import android.app.Activity;
import android.content.Intent;

import edu.gatech.cs2340.cs2340project.presentation.view.activities.LoginActivity;

public class IntentUtil {

    public static void moveBackLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
