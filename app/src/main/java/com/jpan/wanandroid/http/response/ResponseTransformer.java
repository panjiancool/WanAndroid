package com.jpan.wanandroid.http.response;

import com.jpan.wanandroid.http.exception.ApiException;
import com.jpan.wanandroid.http.exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * rxjava2变换封装，分离数据和异常
 */
public class ResponseTransformer {
    public static <T> ObservableTransformer<Response<T>, T> handleResult() {
        return new MyObservableTransformer();
    }

    static class MyObservableTransformer<T> implements ObservableTransformer<Response<T>, T> {

        @Override
        public ObservableSource<T> apply(Observable<Response<T>> upstream) {
            return upstream.onErrorResumeNext(new ErrorResumeFunction()).flatMap(new ResponseFunction());
        }
    }

    /**
     * 非服务器产生的异常，比如解析错误，网络连接错误
     */
    static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<Response<T>>> {

        @Override
        public ObservableSource<Response<T>> apply(Throwable throwable) {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务器产生的错误：HTTP错误代码
     */
    static class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> tResponse) {
            int code = tResponse.getErrorCode();
            return code >= 0 ? Observable.just(tResponse.getData()) :
                    Observable.error(new ApiException(tResponse.getErrorMsg(), null, code));
        }
    }
}
