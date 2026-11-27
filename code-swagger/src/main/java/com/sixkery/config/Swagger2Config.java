package com.sixkery.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * API 文档配置
 *
 * @author sixkery
 * @date 2020/7/23
 */
@Configuration
public class Swagger2Config {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("xxx项目文档")
                        .description("这是一个简单的 Swagger API 演示")
                        .version("1.0.0-SNAPSHOT")
                        .contact(new Contact()
                                .name("sixkery")
                                .url("http://sixkery.top")
                                .email("sixkery@163.com")));
    }
}
