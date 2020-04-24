package com.common.consumer.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-alibaba-provider")
public interface HomeProvider {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    String hello(@RequestBody String name );
}
