package com.jpan.wanandroid.http.exception;

public class ApiException extends Exception {
    private int code;

    public ApiException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
}
