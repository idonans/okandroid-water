package com.okandroid.water.module.splash;

import android.content.Context;
import android.content.Intent;

import com.okandroid.boot.app.ext.preload.PreloadFragment;
import com.okandroid.water.app.BaseActivity;

/**
 * Created by idonans on 2017/5/10.
 */
public class SplashActivity extends BaseActivity {

    public static Intent startIntent(Context context) {
        Intent starter = new Intent(context, SplashActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return starter;
    }

    @Override
    protected PreloadFragment createPreloadFragment() {
        return SplashFragment.newInstance();
    }

}