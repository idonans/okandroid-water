package com.okandroid.water.module.main;

import android.Manifest;

import com.okandroid.water.app.BaseViewProxy;

/**
 * Created by idonans on 2017/5/10.
 */
public class MainViewProxy extends BaseViewProxy<MainView> {

    private static final String[] ALL_PERMISSIONS = {
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.DISABLE_KEYGUARD
    };

    public MainViewProxy(MainView view) {
        super(view);
    }

    @Override
    protected void onPreDataLoadBackground() {
    }

    @Override
    public void onPrepared() {
        super.onPrepared();

        MainView view = getView();
        if (view != null) {
            view.checkAllPermissions(ALL_PERMISSIONS);
        }
    }

}
