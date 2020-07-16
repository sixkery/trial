package com.sixkery.entity;

import lombok.Data;

/**
 * <p>
 * 表格请求参数
 * </p>

 */
@Data
public class TableRequest {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * jdbc-前缀
     */
    private String prepend;
    /**
     * jdbc-url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 表名
     */
    private String tableName;
}
