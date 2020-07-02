package com.sixkery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixkery.entity.User;
import com.sixkery.model.UserModel;
import com.sixkery.web.UserFrom;

import java.util.List;


/**
 * <p>
 * 系统管理-用户基础信息表 服务类
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
public interface UserService extends IService<User> {

    /**
     * 新增用户
     *
     * @param from 用户model
     * @return 是否新增成功
     */
    boolean inset(UserFrom from);

    List<UserModel> findAll();

}
