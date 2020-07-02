package com.sixkery.web.blog;


import com.sixkery.model.BlogModel;
import com.sixkery.service.BlogService;
import com.sixkery.util.ResponseModel;
import com.sixkery.util.ResponseModels;
import com.sixkery.util.page.PageInfo;
import com.sixkery.util.page.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @GetMapping
    public ResponseModel<PageInfo<BlogModel>> findAll(Pageable pageable) {
        return ResponseModels.ok(blogService.findAll(pageable));
    }

    @PostMapping
    public ResponseModel<Boolean> inset(@RequestBody @Validated BlogFrom blogFrom) {
        return ResponseModels.ok(blogService.insert(blogFrom));
    }
    @GetMapping("/{blogId}")
    public ResponseModel<BlogModel> findOne(@PathVariable String blogId) {
        return ResponseModels.ok(blogService.findOne(blogId));
    }

}
