package com.mall.common.commons.congif;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 邮箱配置类
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mall.email")
public class EmailProperties {
    /**
     * 邮箱通知列表
     */
    private List<String> emailGroup;


}
