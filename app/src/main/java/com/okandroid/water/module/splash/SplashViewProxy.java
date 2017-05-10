package com.okandroid.water.module.splash;

import com.okandroid.boot.thread.Threads;
import com.okandroid.water.app.BaseViewProxy;

/**
 * Created by idonans on 2017/5/10.
 */
public class SplashViewProxy extends BaseViewProxy<SplashView> {

    public SplashViewProxy(SplashView splashView) {
        super(splashView);
    }

    @Override
    protected void onPreDataLoadBackground() {
        Threads.sleepQuietly(2000);
    }

    @Override
    public void onPrepared() {
        super.onPrepared();

        SplashView view = getView();
        if (view == null) {
            return;
        }

        view.directToMain();
    }

}
