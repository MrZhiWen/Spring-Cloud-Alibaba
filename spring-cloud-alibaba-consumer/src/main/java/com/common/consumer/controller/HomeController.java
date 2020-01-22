package com.common.consumer.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RefreshScope
public class HomeController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@RequestParam(name = "name") String name ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-alibaba-provider");
        String url = serviceInstance.getUri() + "/hello";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = Maps.newHashMap();
        map.put("name",name);
        HttpEntity httpEntity = new HttpEntity(map,headers);
        System.out.println("请求地址:"+serviceInstance.getUri().getPort());
        //注意次调用方式 在客户端注解一定要 使用 @RequestBody 注解
        ResponseEntity<String> result = restTemplate.postForEntity(url,httpEntity,String.class);
        String msg = "欢迎请求进入 hello "+result.getBody();
        System.out.println(msg);
        return msg;
    }
}
