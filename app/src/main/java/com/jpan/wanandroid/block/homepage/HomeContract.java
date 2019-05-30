package com.jpan.wanandroid.block.homepage;

import com.jpan.wanandroid.base.BasePresenter;
import com.jpan.wanandroid.block.homepage.entry.HomeListBean;
import com.jpan.wanandroid.http.response.Response;

import io.reactivex.Observable;

/**
 * 首页数据
 */
public interface HomeContract {
    interface View {
        boolean getHomeListSuccess(HomeListBean homeListBean, boolean isRefresh);

        boolean getHomeListFail(String msg, boolean isRefresh);
    }

    interface mode {
        Observable<Response<HomeListBean>> getHomeList(int page);
    }

    interface Presenter extends BasePresenter {
        void getHomeList(boolean isRefresh);
    }
}
