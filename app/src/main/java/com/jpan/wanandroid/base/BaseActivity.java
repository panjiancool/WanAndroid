package com.jpan.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceId());
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    /**
     * 加载布局资源
     *
     * @return 布局资源id
     */
    protected abstract int setLayoutResourceId();

    /**
     * 初始化布局相关
     */
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
