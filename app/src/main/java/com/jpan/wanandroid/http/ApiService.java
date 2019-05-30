package com.jpan.wanandroid.http;

import com.jpan.wanandroid.block.homepage.entry.HomeListBean;
import com.jpan.wanandroid.http.response.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    /**
     * 首页文章列表
     * https://www.wanandroid.com/article/list/0/json
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始
     */
    @GET("/article/list/{page}/json")
    Observable<Response<HomeListBean>> getHomeList(@Path("page") int page);
}
