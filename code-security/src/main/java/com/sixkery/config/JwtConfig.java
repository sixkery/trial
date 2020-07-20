package com.sixkery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liugang
 * @date 2020/7/16
 */
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {

    /**
     * jwt 加密 key ,默认值 sixkery
     */
    private String key = "sixkery";
    /**
     * 过期时间 默认值 10 分钟
     */
    private Long expired = 600000L;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 7天
     */
    private Long remember = 604800000L;

}
