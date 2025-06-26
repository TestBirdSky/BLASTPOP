package com.unity3d.player;

import android.app.Application;


/**
 * Date：2025/4/22
 * Describe:
 */
public class AppPlayer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UnityHelper uh=new UnityHelper();
        uh.a(this);
    }
}
