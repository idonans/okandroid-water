package com.okandroid.water;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.okandroid.boot.lang.Log;
import com.okandroid.water.module.splash.SplashActivity;

/**
 * Created by idonans on 2017/5/10.
 */

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG + " onReceive");

        context.startActivity(SplashActivity.startIntent(context));
    }

}
