package com.sixkery.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sixkery
 * @date 2020/6/28
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置分页拦截器
     *
     * @return 分页拦截器的Bean
     */
    @Bean
    PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
