package com.okandroid.water.data;

import android.app.KeyguardManager;
import android.app.Notification;
import android.content.Context;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.okandroid.boot.AppContext;
import com.okandroid.water.R;

/**
 * Created by idonans on 2017/5/10.
 */

public class WaterManager {

    private static final String TAG = "WaterManager";

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

    private final ForceWakeLock mWakeLock;
    private final KeyguardManager.KeyguardLock mKeyguardLock;

    private WaterManager() {
        Context context = AppContext.getContext();

        mWakeLock = new ForceWakeLock();

        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        mKeyguardLock = km.newKeyguardLock(TAG);

        showLockNotification();

        unlockScreen();
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

    /**
     * 保持屏幕唤醒, 关闭屏保
     */
    public void unlockScreen() {
        safetyRun(new Runnable() {
            @Override
            public void run() {
                mWakeLock.acquire();
            }
        });

        safetyRun(new Runnable() {
            @Override
            public void run() {
                mKeyguardLock.disableKeyguard();
            }
        });
    }

    /**
     * 开启屏保
     */
    public void lockScreen() {
        safetyRun(new Runnable() {
            @Override
            public void run() {
                mWakeLock.release();
            }
        });

        safetyRun(new Runnable() {
            @Override
            public void run() {
                mKeyguardLock.reenableKeyguard();
            }
        });
    }

    private class ForceWakeLock {

        private final PowerManager.WakeLock mWakeLock;

        private ForceWakeLock() {
            Context context = AppContext.getContext();

            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, TAG);
            mWakeLock.setReferenceCounted(false);
        }

        public void acquire() {
            mWakeLock.acquire();
        }

        public void release() {
            mWakeLock.release();
        }

    }

    private static void safetyRun(Runnable runnable) {
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}
