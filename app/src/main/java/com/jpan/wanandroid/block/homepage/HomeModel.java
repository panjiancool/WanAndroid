package com.jpan.wanandroid.block.homepage;

import com.jpan.wanandroid.http.ApiManager;
import com.jpan.wanandroid.block.homepage.entry.HomeListBean;
import com.jpan.wanandroid.http.response.Response;

import io.reactivex.Observable;

public class HomeModel implements HomeContract.mode {

    @Override
    public Observable<Response<HomeListBean>> getHomeList(int page) {
        return ApiManager.getInstance().getService().getHomeList(page);
    }

}
