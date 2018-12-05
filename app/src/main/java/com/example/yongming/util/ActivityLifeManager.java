package com.example.yongming.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityLifeManager {


    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }


    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void closeAllActivities()
    {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}