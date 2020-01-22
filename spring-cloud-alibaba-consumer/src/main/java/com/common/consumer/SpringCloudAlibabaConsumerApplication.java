package com.common.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author lizhiwen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaConsumerApplication.class, args);
    }

}
