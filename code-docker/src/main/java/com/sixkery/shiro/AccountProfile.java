package com.sixkery.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sixkery
 * @date 2020/6/14
 */
@Data
public class AccountProfile implements Serializable {
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

}
