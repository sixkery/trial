package com.sixkery.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 *
 * @author sixkery
 * @date 2020/7/23
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "dev")
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sixkery.web"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("xxx项目文档")
                .description("这是一个简单的 Swagger API 演示")
                .contact(new Contact("sixkery", "http://sixkery.top", "sixkery@163.com"))
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    // 给请求加参数的
//    private Parameter buildAuthParameter() {
//        return new ParameterBuilder()
//                .name("Authorization")
//                .defaultValue("Bearer " + this.generateJWT())
//                .description("Authorization")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//    }

    // 添加 jwt
//    private String generateJWT() {
//        byte[] secretKey = securityProperties.getJwt().getSecretKey().getBytes();
//        return Jwts.builder()
//                .claim("username", userProperties.getAdmin())
//                .signWith(
//                        SignatureAlgorithm.HS256,
//                        Base64.getEncoder().encodeToString(secretKey)
//                )
//                .compact();
//    }
}
