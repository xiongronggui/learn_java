package com.panda.rabbitmq.common;

import com.alibaba.fastjson.JSON;

public class Result<T>
{
    private int code;
    private String message;
    private T data;
    public Result setCode(ResultCode resultCode)
    {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public  enum  ResultCode
    {
        SUCCESS(200),
        FAIL(400),
        UNAUTHORIZED(401),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500);

        public  int code;

        ResultCode(int code){
            this.code = code;
        }
    }
}
