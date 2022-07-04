package com.geo.mvpframe_maters.utils;

import android.app.Activity;

import java.util.HashSet;

public class ActivityManageUtil {

    private static ActivityManageUtil instance = new ActivityManageUtil();
    private static HashSet<Activity> hashSet = new HashSet<>();
    private ActivityManageUtil(){

    }
    public static ActivityManageUtil getInstance() {
        return instance;
    }

    /**
     * 每一个Activity 在 onCreate 方法的时候，可以装入当前this
     * @param activity
     */
    public void addActivity(Activity activity) {
        if ("activity.MainActivity.MainActivity".equals(activity.getLocalClassName())){
            return;
        }
        try {
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeActivity(Activity activity) {
        try {
            hashSet.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用此方法用于销毁所有的Activity，然后我们在调用此方法之前，调到登录的Activity
     */
    public void exit() {
        try {
            for (Activity activity : hashSet) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

