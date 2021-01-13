package com.yuqi.demo.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装layui页面出参
 *
 * @author yuqi
 * @date 2019-11-26 下午 5:14
 */
@Data
@NoArgsConstructor
public class Result<T> {
    /**
     * 调用成功
     */
    public static final Integer RESULT_SUCCESS = 0;

    /**
     * 调用失败
     */
    public static final Integer RESULT_FAILURE = -1;

    public static final String RESULT_SUCCESS_MSG = "请求成功";

    public static final String RESULT_FAILURE_MSG = "系统繁忙";

    /**
     * 总数
     */
    private Long count;
    /**
     * 状态：0成功-1失败
     */
    private Integer code;
    /**
     * 成功或者失败返回的消息
     */
    private String msg;
    /**
     * 成功后返回的数据
     */
    private T data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> ok() {
        return restResult(null, RESULT_SUCCESS, RESULT_SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, RESULT_SUCCESS, RESULT_SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, RESULT_SUCCESS, msg);
    }

    public static <T> Result<T> ok(String msg) {
        return restResult(null, RESULT_SUCCESS, msg);
    }

    public static <T> Result<T> fail() {
        return restResult(null, RESULT_FAILURE, RESULT_FAILURE_MSG);
    }

    public static <T> Result<T> fail(String msg) {
        return restResult(null, RESULT_FAILURE, msg);
    }

    public static <T> Result<T> fail(T data) {
        return restResult(data, RESULT_FAILURE, RESULT_FAILURE_MSG);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return restResult(data, RESULT_FAILURE, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
