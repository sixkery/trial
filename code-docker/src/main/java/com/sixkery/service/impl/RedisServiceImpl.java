package com.sixkery.service.impl;

import com.sixkery.service.RedisService;
import org.springframework.stereotype.Service;

/**
 * @author sixkery
 * @date 2020/4/16
 * redis 操作 Service 实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public void set(String key, String value) {
//        stringRedisTemplate.opsForValue().set(key, value);
//
//    }
//
//    @Override
//    public String get(String key) {
//        return stringRedisTemplate.opsForValue().get(key);
//    }
//
//    @Override
//    public boolean expire(String key, Long expire) {
//        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void remove(String key) {
//        stringRedisTemplate.delete(key);
//
//    }
//
//    @Override
//    public Long increment(String key, long delta) {
//        return stringRedisTemplate.opsForValue().increment(key, delta);
//    }
}
