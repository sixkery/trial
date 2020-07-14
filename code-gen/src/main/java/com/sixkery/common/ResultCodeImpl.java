package com.sixkery.common;

import lombok.Getter;

/**
 * <p>
 * 通用状态枚举
 * </p>
 */
@Getter
public enum ResultCodeImpl implements ResultCode {
    /**
     * 成功
     */
    OK(200, "成功"),
    /**
     * 失败
     */
    ERROR(500, "失败");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    ResultCodeImpl(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
