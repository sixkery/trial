package com.sixkery.service;

import com.sixkery.CodeRedisApplicationTests;
import com.sixkery.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liugang
 * @date 2020/7/10
 */

@Slf4j
public class UserServiceImplTest extends CodeRedisApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void getTwice() {
        User user = userService.get(1L);
        log.info("获取一次数据 user={}", user);

        User user2 = userService.get(1L);
        log.info("获取第二次数据 user={}", user2);

    }

}