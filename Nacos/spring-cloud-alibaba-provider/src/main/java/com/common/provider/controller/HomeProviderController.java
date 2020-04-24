package com.common.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class HomeProviderController {

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@RequestBody String name ){
        return "name="+name;
    }
}
