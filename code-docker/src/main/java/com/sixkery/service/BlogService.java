package com.sixkery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixkery.entity.Blog;
import com.sixkery.model.BlogModel;
import com.sixkery.util.page.PageInfo;
import com.sixkery.util.page.Pageable;
import com.sixkery.web.blog.BlogFrom;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
public interface BlogService extends IService<Blog> {


    /**
     * 新增一条博客
     *
     * @param blogFrom 博客 form
     * @return 是否新增成功
     */
    Boolean insert(BlogFrom blogFrom);


    /**
     * 查询全部的blog
     *
     * @return blog 列表
     */
    PageInfo<BlogModel> findAll(Pageable pageable);

    /**
     * 查询全部的blog
     *
     * @return blog 列表
     */
    BlogModel findOne(String blogId);



}
