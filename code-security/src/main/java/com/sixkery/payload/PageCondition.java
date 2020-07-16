package com.sixkery.payload;

import lombok.Data;

/**
 * <p>
 * 分页请求参数
 * </p>
 * @author liugang
 * @date 2020/7/16
 */
@Data
public class PageCondition {
    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
