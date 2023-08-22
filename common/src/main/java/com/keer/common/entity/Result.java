package com.keer.common.entity;

import java.io.Serializable;

public class Result<T> implements Serializable {
    public static final long serialVersionUID = 42L;

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    /**
     * 状态码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String msg;
    /**
     * 数据对象
     */
    protected T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<T>(data);
    }

    public static Result error() {
        return new Result(ERROR_CODE, "操作失败");
    }

    public static Result error(String msg) {
        return new Result(ERROR_CODE, msg);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    @Override
    public String toString() {
        return "ResultT [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
