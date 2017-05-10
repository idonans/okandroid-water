package com.okandroid.water.module.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.okandroid.boot.app.ext.preload.PreloadViewProxy;
import com.okandroid.boot.util.IOUtil;
import com.okandroid.water.R;
import com.okandroid.water.app.BaseFragment;

/**
 * Created by idonans on 2017/5/10.
 */
public class MainFragment extends BaseFragment implements MainView {

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

    private class Content extends PreloadSubViewHelper {

        private Content(@NonNull Activity activity, @NonNull LayoutInflater inflater, @NonNull ViewGroup contentView) {
            super(activity, inflater, contentView, R.layout.water_main_view);
        }

    }

}
