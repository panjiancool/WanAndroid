package com.jpan.wanandroid.http.exception;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class CustomException {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1000;
    /**
     * HTTP错误
     */
    public static final int HTTP_ERROR = 1003;

    /**
     * 将本地异常解析成ApiException
     */
    public static ApiException handleException(Throwable cause) {
        ApiException exception;
        if (cause instanceof JsonParseException) {
            exception = new ApiException(cause.getMessage(), cause, PARSE_ERROR);
        } else if (cause instanceof UnknownHostException ||
                cause instanceof SocketTimeoutException ||
                cause instanceof ConnectException) {
            exception = new ApiException(cause.getMessage(), cause, NETWORK_ERROR);
        } else {
            exception = new ApiException(cause.getMessage(), cause, UNKNOWN);
        }
        return exception;
    }

}
