package com.okandroid.water.util;

import android.content.Context;
import android.widget.Toast;

import com.okandroid.boot.AppContext;
import com.okandroid.boot.thread.Threads;

/**
 * Created by idonans on 2017/5/10.
 */

public class MessageUtil {

    private MessageUtil() {
    }

    public static void showPermissionsErrorMessage() {
        showMessage("权限不足, 请重新配置或联系客服");
    }

    public static void showMessage(final CharSequence message) {
        Threads.runOnUi(new Runnable() {
            @Override
            public void run() {
                Context context = AppContext.getContext();
                Toast.makeText(context, String.valueOf(message), Toast.LENGTH_LONG).show();
            }
        });
    }

}
