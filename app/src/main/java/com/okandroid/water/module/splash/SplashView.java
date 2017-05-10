package com.okandroid.water.module.splash;

import com.okandroid.water.app.BaseView;

/**
 * Created by idonans on 2017/5/10.
 */
public interface SplashView extends BaseView {

    /**
     * @return direct success return true, otherwise return false.
     */
    boolean directToMain();
}
