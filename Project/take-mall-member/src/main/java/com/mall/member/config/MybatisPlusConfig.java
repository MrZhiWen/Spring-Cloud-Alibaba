package com.mall.member.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlusConfig
 *
 * @author lizhiwen
 * @since 2020/3/28 3:23 下午
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mall.common.**.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     *
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author lizhiwen
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}