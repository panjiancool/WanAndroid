package com.jpan.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View currentView = inflater.inflate(setLayoutResourceId(), container, false);
        mUnbinder = ButterKnife.bind(this, currentView);
        initView(currentView);
        return currentView;
    }

    /**
     * 加载布局资源
     *
     * @return 布局资源id
     */
    protected abstract int setLayoutResourceId();

    protected abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
