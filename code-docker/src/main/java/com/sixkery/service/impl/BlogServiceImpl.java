package com.sixkery.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sixkery.dao.BlogMapper;
import com.sixkery.entity.Blog;
import com.sixkery.model.BlogModel;
import com.sixkery.service.BlogService;
import com.sixkery.util.page.PageInfo;
import com.sixkery.util.page.Pageable;
import com.sixkery.web.blog.BlogFrom;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageInfo<BlogModel> findAll(Pageable pageable) {

        Page<BlogModel> page = PageHelper.startPage(pageable.getPage(), pageable.getSize())
                .setOrderBy(pageable.getSort())
                .doSelectPage(() -> blogMapper.selectList(Wrappers.<Blog>query()));

        PageInfo<BlogModel> pageInfo = new PageInfo<>();
        pageInfo.setContent(page.getResult());
        pageInfo.setPage(page.getPageNum());
        pageInfo.setSize(page.getPageSize());
        pageInfo.setTotalPage(page.getPages());
        pageInfo.setTotalRecords((int) page.getTotal());


        return pageInfo;
    }

    @Override
    public BlogModel findOne(String blogId) {
        BlogModel blogModel = new BlogModel();
        Blog blog = getById(blogId);
        BeanUtils.copyProperties(blog, blogModel);

        return blogModel;
    }

    @Override
    public Boolean insert(BlogFrom blogFrom) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogFrom, blog);

        blog.setUserId(5L);
        blog.setCreated(LocalDateTime.now());
        return save(blog);
    }
}

