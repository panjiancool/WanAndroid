package com.jpan.wanandroid.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API请求管理服务
 */
public class ApiManager {

    private static final String endpoint = ApiConstants.BASE_URL;
    private static final int CONNECT_TIME_OUT = 15;// second

    private static ApiManager mApiManager;

    private OkHttpClient client;
    private Retrofit retrofit;
    private ApiService service;
    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    // 能够详尽的追踪访问链接的重定向
    // 短时间内的网络访问，它将不执行缓存过来的回应
    // 监测整个网络过程的数据流量
    private Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            // TODO 按实际项目需求配置
            String mobile_version = android.os.Build.VERSION.RELEASE;
            Request builder = chain.request().newBuilder().
                    addHeader("version", mobile_version).build();
            return chain.proceed(builder);
        }
    };

    /**
     * 获取单例
     */
    public static ApiManager getInstance() {
        if (mApiManager == null) {
            synchronized (ApiManager.class) {
                if (mApiManager == null) {
                    mApiManager = new ApiManager();
                }
            }
        }
        return mApiManager;
    }


    /***
     * 单例 全局使用一个实例
     *
     * @return ApiService
     */
    public ApiService getService() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    // 设置应用拦截器，可用于设置公共参数，头信息，日志拦截
                    .addInterceptor(interceptor)
                    // 错误重联
                    .retryOnConnectionFailure(true)
                    // 配置连接超时
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    // 网络拦截器，可以用于重试或重写
                    .addNetworkInterceptor(networkInterceptor)
                    .build();
        }

        if (retrofit == null) {
            // 初始化Retrofit实例
            retrofit = new Retrofit.Builder()
                    // 相当于1.9中的setEndPoint
                    .baseUrl(endpoint)
                    // 设置OKHttpClient,如果不设置会提供一个默认的
                    .client(client)
                    // 设置使用Gson解析,可以添加多种序列化Factory，
                    // 但是GsonConverterFactory必须放在最后,否则会抛出异常
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        if (service == null) {
            synchronized (ApiService.class) {
                if (service == null) {
                    service = retrofit.create(ApiService.class);
                }
            }
        }
        return service;
    }
}