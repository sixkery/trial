package com.sixkery.service;

import com.sixkery.CodeRedisApplicationTests;
import com.sixkery.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liugang
 * @date 2020/7/10
 */

public class UserServiceImplTest extends CodeRedisApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void getTwice() {
        User user = userService.get(1L);

    }

}