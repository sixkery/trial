package com.sixkery.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sixkery.dao.UserMapper;
import com.sixkery.entity.User;
import com.sixkery.model.UserModel;
import com.sixkery.service.UserService;
import com.sixkery.web.UserFrom;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 系统管理-用户基础信息表 服务实现类
 * </p>
 *
 * @author sixkery
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public boolean inset(UserFrom from) {
        User user = new User();
        BeanUtils.copyProperties(from, user);

        user.setPassword(SecureUtil.md5(user.getPassword()));

        int insert = userMapper.insert(user);
        return insert != 0;

    }

    @Override
    public List<UserModel> findAll() {
        List<UserModel> userModels = new ArrayList<>();
        List<User> users = userMapper.selectList(Wrappers.<User>query(null));
        users.forEach(user -> {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel);
            userModels.add(userModel);
        });
        return userModels;
    }
}
