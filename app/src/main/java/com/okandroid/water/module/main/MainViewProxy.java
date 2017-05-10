package com.okandroid.water.module.main;

import com.okandroid.water.app.BaseViewProxy;
import com.okandroid.water.data.WaterManager;

/**
 * Created by idonans on 2017/5/10.
 */
public class MainViewProxy extends BaseViewProxy<MainView> {

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
            if (!WaterManager.getInstance().isAllPermissionsGranted()) {
                view.checkAllPermissions(WaterManager.getAllPermissions());
            } else {
                view.closeSelf();
            }
        }
    }

}
