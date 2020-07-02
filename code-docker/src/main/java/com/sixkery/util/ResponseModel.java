package com.sixkery.util;

import lombok.Data;

import java.util.Date;

/**
 * @author sixkery
 * @date 2020/6/4
 * <p>
 * 全局请求对象返回数据模型
 */
@Data
public class ResponseModel<T> {

    private int code;

    private String message;

    private T data;

    private long timestamp = new Date().getTime();

    public ResponseModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

