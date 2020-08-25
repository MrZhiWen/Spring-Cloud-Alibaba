package com.mall.member.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : SwaggerConfig.java
 * @Description : Swagger2 api 文档生成工具配置
 * @Author : lizhiwen
 * @Date: 2020-08-21 08:27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 各环境之间开启 禁用api文档
     */
    @Value("${spring.swagger.enabled}")
    private Boolean swaggerEnabled;
    /**
     * 扫描接口 包路径
     */
    @Value("${spring.swagger.basePackage}")
    private String basePackage;
    /**
     * 接口文档  title
     */
    @Value("${spring.swagger.title}")
    private String title;
    /**
     * 项目名称
     */
    @Value("${spring.application.name}")
    private String name;


    /**
     * 创建 Docket
     * 用于配置swagger2，包含文档基本信息
     * 指定swagger2的作用域（这里指定包路径下的所有API）
     * @return
     */
    @Bean
    public Docket createResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(name)
//                //团队地址
//                .termsOfServiceUrl("")
                // 设置联系方式
                .contact(new Contact("lizhiwen", "", "719272090@qq.com"))
                .version("1.0")
                .build();
    }
}
