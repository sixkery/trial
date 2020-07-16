package com.sixkery.service;

import com.sixkery.CodeRedisApplicationTests;
import com.sixkery.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sixkery
 * @date 2020/7/10
 */

@Slf4j
public class UserServiceImplTest extends CodeRedisApplicationTests {
    @Autowired
    private UserService userService;

    /**
     * 查询两次，第一次查询数据库，第二次查询缓存
     */
    @Test
    public void getTwice() {
        User user = userService.get(5L);
        log.info("获取一次数据 user={}", user);

        User user2 = userService.get(5L);
        log.info("获取第二次数据 user={}", user2);

        // 查看日志，只打印一次日志，证明缓存生效
    }

    /**
     * 测试先保存再查询
     */
    @Test
    public void getAfterSave() {
        userService.saveOrUpdate(new User(5L, "测试先保存再查询"));
        User user = userService.get(5L);
        log.info("user= {}", user);
        // 打印保存的日志，不打印查询的日志，缓存生效
    }

    @Test
    public void getAfterDel() {
        // 查询一次，使redis中存在缓存数据
        User user = userService.get(1L);
        log.info("user={}", user);
        // 删除，查看redis是否存在缓存数据
        userService.delete(1L);
    }

}