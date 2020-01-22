package com.common.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudAlibabaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaProducerApplication.class, args);
    }

}
