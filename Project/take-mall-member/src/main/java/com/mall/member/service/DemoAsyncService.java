package com.mall.member.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName : DemoAsyncService.java
 * @Description : spring boot 异步处理 案例
 * @Author : lizhiwen
 * @Date: 2020-08-20 15:50
 */
@Service
@Slf4j
public class DemoAsyncService {

    @Async
    public void addScore(){
        //TODO 添加积分 休眠5秒钟在执行
        try {
            Thread.sleep(1000*5);
            log.info("---------处理积分--------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Async("asyncPoolTaskExecutor")
    public void addScore1(){
        //TODO 添加积分 休眠5秒钟在执行
        try {
            Thread.sleep(1000*6);
            log.info("---------处理积分--------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Async("redisPoolTaskExecutor")
    public void addScore2(){
        //TODO 添加积分 休眠5秒钟在执行
        try {
            Thread.sleep(1000*5);
            log.info("---------处理redis 缓存--------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
