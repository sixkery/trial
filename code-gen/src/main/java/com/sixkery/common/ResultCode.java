package com.sixkery.common;

/**
 * <p>
 * 统一状态码接口
 * </p>
 */
public interface ResultCode {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获取返回消息
     *
     * @return 返回消息
     */
    String getMessage();
}
