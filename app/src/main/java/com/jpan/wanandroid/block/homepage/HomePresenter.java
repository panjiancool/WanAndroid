package com.jpan.wanandroid.block.homepage;

import com.jpan.wanandroid.base.BaseSchedulerProvider;
import com.jpan.wanandroid.http.response.ResponseTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter implements HomeContract.Presenter {

    private int page;

    private BaseSchedulerProvider scheduler;

    private HomeContract.View view;

    private HomeModel model = new HomeModel();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomePresenter(BaseSchedulerProvider scheduler, HomeContract.View view) {
        this.view = view;
        this.scheduler = scheduler;
    }

    @Override
    public void getHomeList(final boolean isRefresh) {
        if (isRefresh) {
            page = 0;
        }
        Disposable disposable = model.getHomeList(page)
                .compose(ResponseTransformer.handleResult())
                .compose(scheduler.applySchedulers())
                .subscribe(homeListBean ->
                {
                    if (view.getHomeListSuccess(homeListBean, isRefresh)) {
                        page++;
                    }
                }, throwable -> view.getHomeListFail(throwable.getMessage(), isRefresh));
        // 添加订阅
        compositeDisposable.add(disposable);
    }

    @Override
    public void unSubscribe() {
        // 解除订阅
        compositeDisposable.dispose();
    }
}
