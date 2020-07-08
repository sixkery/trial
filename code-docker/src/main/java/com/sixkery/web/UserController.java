package com.sixkery.web;


import com.sixkery.entity.User;
import com.sixkery.model.UserModel;
import com.sixkery.service.UserService;
import com.sixkery.util.ResponseModel;
import com.sixkery.util.ResponseModels;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统管理-用户基础信息表 前端控制器
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping
    public ResponseModel<Boolean> save(@Validated @RequestBody UserFrom from) {
        return ResponseModels.ok(userService.inset(from));
    }

    @GetMapping("/index")
    public ResponseModel<User> getById() {
        return ResponseModels.ok(userService.getById(1L));
    }

    @GetMapping("/list")
    public ResponseModel<List<UserModel>> list() {
        return ResponseModels.ok(userService.findAll());
    }

}
