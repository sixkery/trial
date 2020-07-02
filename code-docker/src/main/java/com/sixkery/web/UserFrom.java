package com.sixkery.web;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author sixkery
 * @date 2020/6/14
 */
@Data
public class UserFrom {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String nickname;

    private String avatar;

    @Email(message = "邮箱格式不正确!")
    private String email;
}
