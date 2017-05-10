package com.okandroid.water.module.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.okandroid.boot.app.ext.preload.PreloadViewProxy;
import com.okandroid.boot.util.IOUtil;
import com.okandroid.boot.util.SystemUtil;
import com.okandroid.water.R;
import com.okandroid.water.app.BaseFragment;

/**
 * Created by idonans on 2017/5/10.
 */
public class MainFragment extends BaseFragment implements MainView {

    private static final int REQUEST_PERMISSION_CODE_ALL = 1;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public MainViewProxy getDefaultViewProxy() {
        return (MainViewProxy) super.getDefaultViewProxy();
    }

    @Override
    protected PreloadViewProxy newDefaultViewProxy() {
        return new MainViewProxy(this);
    }

    private Content mContent;

    @Override
    protected void showPreloadContentView(@NonNull Activity activity, @NonNull LayoutInflater inflater, @NonNull ViewGroup contentView) {
        IOUtil.closeQuietly(mContent);
        mContent = new Content(activity, inflater, contentView);
    }

    @Override
    public void checkAllPermissions(final String[] permissions) {
        Context context = SystemUtil.getActivityFromFragment(this);
        new AlertDialog.Builder(context)
                .setTitle("系统配置")
                .setMessage("为何确保系统功能正常使用, 需要使用必要的权限。")
                .setNegativeButton("不使用系统功能", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ignore
                    }
                })
                .setPositiveButton("开始配置权限", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(permissions, REQUEST_PERMISSION_CODE_ALL);
                    }
                });
    }

    private class Content extends PreloadSubViewHelper {

        private Content(@NonNull Activity activity, @NonNull LayoutInflater inflater, @NonNull ViewGroup contentView) {
            super(activity, inflater, contentView, R.layout.water_main_view);
        }

    }

}
