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
    }

    @Override
    public void onPrepared() {
        super.onPrepared();

        SplashView view = getView();
        if (view == null) {
            return;
        }

        Threads.postUi(new Runnable() {
            @Override
            public void run() {
                SplashView view = getView();
                if (view == null) {
                    return;
                }
                view.directToMain();
            }
        }, 3000);
    }
}
