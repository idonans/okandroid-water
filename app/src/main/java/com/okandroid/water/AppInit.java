package com.okandroid.water;

import android.content.Context;
import android.os.Build;

import com.okandroid.boot.App;
import com.okandroid.boot.data.AppIDManager;
import com.okandroid.boot.data.FrescoManager;
import com.okandroid.boot.data.ProcessManager;
import com.okandroid.boot.data.StorageManager;
import com.okandroid.boot.lang.Log;
import com.okandroid.boot.thread.Threads;
import com.okandroid.water.data.WaterManager;

import java.util.Locale;

/**
 * Created by idonans on 2017/5/10.
 */

public class AppInit {

    private static final String TAG = "AppInit";
    private static boolean sInit;

    private AppInit() {
    }

    public synchronized static void init(Context context) {
        if (sInit) {
            return;
        }

        context = context.getApplicationContext();

        new App.Config.Builder()
                .setContext(context)
                .setBuildConfigAdapter(new BuildConfigAdapterImpl())
                .setDefaultUserAgent(getAppGlobalUserAgent(context))
                .build()
                .init();

        initOnMainProcess();

        sInit = true;
    }

    private static String getAppGlobalUserAgent(Context context) {
        return String.format(Locale.getDefault(), "okandroid-water/%s (device %s; Android %s/%s)",
                BuildConfig.VERSION_NAME,
                Build.BRAND,
                Build.VERSION.SDK_INT,
                Build.VERSION.RELEASE);
    }

    private static void initOnMainProcess() {
        if (!ProcessManager.getInstance().isMainProcess()) {
            return;
        }

        Threads.postBackground(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG + " init in background on main process");
                    StorageManager.getInstance();
                    FrescoManager.getInstance();
                    AppIDManager.getInstance();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });

        WaterManager.getInstance();
    }

    public static class BuildConfigAdapterImpl implements App.BuildConfigAdapter {

        @Override
        public int getVersionCode() {
            return BuildConfig.VERSION_CODE;
        }

        @Override
        public String getVersionName() {
            return BuildConfig.VERSION_NAME;
        }

        @Override
        public String getLogTag() {
            return BuildConfig.APPLICATION_ID;
        }

        @Override
        public String getPublicSubDirName() {
            return BuildConfig.APPLICATION_ID;
        }

        @Override
        public String getChannel() {
            return BuildConfig.CHANNEL;
        }

        @Override
        public int getLogLevel() {
            return android.util.Log.DEBUG;
        }

        @Override
        public boolean isDebug() {
            return BuildConfig.DEBUG;
        }
    }

}
