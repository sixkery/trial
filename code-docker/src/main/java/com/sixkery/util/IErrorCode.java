package com.sixkery.util;

/**
 * @author sixkery
 * @date 2020/4/21
 * <p>
 * 封装API的错误码
 */
public interface IErrorCode {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
