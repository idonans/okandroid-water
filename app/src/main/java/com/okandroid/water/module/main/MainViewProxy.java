package com.okandroid.water.module.main;

import com.okandroid.boot.thread.Threads;
import com.okandroid.water.app.BaseViewProxy;

/**
 * Created by idonans on 2017/5/10.
 */
public class MainViewProxy extends BaseViewProxy<MainView> {

    public MainViewProxy(MainView view) {
        super(view);
    }

    @Override
    protected void onPreDataLoadBackground() {
        Threads.sleepQuietly(2000);
    }

    @Override
    public void onPrepared() {
        super.onPrepared();

        // TODO
    }

}
