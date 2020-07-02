package com.sixkery.web.user;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.entity.User;
import com.sixkery.model.UserModel;
import com.sixkery.service.UserService;
import com.sixkery.util.JwtUtils;
import com.sixkery.util.ResponseModel;
import com.sixkery.util.ResponseModels;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseModel<UserModel> login(@Validated @RequestBody LoginFrom from, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", from.getUsername()));
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(SecureUtil.md5(from.getPassword()))) {
            return ResponseModels.failed("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
            return ResponseModels.ok(userModel);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public ResponseModel logout() {
        SecurityUtils.getSubject().logout();
        return ResponseModels.failed();
    }

}
