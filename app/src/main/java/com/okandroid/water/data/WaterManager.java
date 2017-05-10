package com.okandroid.water.data;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.okandroid.boot.AppContext;
import com.okandroid.water.R;

/**
 * Created by idonans on 2017/5/10.
 */

public class WaterManager {

    private static class InstanceHolder {

        private static final WaterManager sInstance = new WaterManager();

    }

    private static boolean sInit;

    public static WaterManager getInstance() {
        WaterManager instance = WaterManager.InstanceHolder.sInstance;
        sInit = true;
        return instance;
    }

    public static boolean isInit() {
        return sInit;
    }

    private static final int LOCK_NOTIFICATION_ID = 1;

    private WaterManager() {

        showLockNotification();
    }

    private void showLockNotification() {
        Context context = AppContext.getContext();
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle("water")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("water going")
                .setOngoing(true)
                .setAutoCancel(false)
                .build();
        NotificationManagerCompat.from(context).notify(LOCK_NOTIFICATION_ID, notification);
    }

}
