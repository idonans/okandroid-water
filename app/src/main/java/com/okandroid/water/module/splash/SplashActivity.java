package com.okandroid.water.module.splash;

import com.okandroid.boot.app.ext.preload.PreloadFragment;
import com.okandroid.water.app.BaseActivity;

/**
 * Created by idonans on 2017/5/10.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected PreloadFragment createPreloadFragment() {
        return SplashFragment.newInstance();
    }

}