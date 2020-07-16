package com.sixkery.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花主键生成器
 *
 * @author liugang
 * @date 2020/7/16
 */
@Configuration
public class IdConfig {

    /**
     * 雪花算法生成器
     *
     * @return 生成雪花算法
     */
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}
