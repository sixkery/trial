package com.sixkery.web.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author sixkery
 * @date 2020/6/14
 */
@Data
public class LoginFrom {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
