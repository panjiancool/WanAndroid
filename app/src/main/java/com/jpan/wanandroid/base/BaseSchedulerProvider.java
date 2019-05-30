package com.jpan.wanandroid.base;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * 线程切换拓展类
 */
public interface BaseSchedulerProvider {

    public Scheduler computation();

    public Scheduler io();

    public Scheduler ui();

    public <T> ObservableTransformer<T, T> applySchedulers();
}
