package com.okandroid.water;

import android.app.Application;

/**
 * Created by idonans on 2017/5/10.
 */

public class WaterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInit.init(this);
    }

}
