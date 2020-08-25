package com.mall.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @ClassName : AsyncConfiguration.java
 * @Description : 异步开启配置,添加到IOC容器中  自定义 线程池
 * @Author : lizhiwen
 * @Date: 2020-08-20 16:22
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {
    @Bean(name = "asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor getPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池数量
        taskExecutor.setCorePoolSize(10);
        //设置线程池 维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(100);
        //设置线程 缓存队列
        taskExecutor.setQueueCapacity(50);
        //设置线程 空闲时间,当超过了核心线程数 之外的线程在空闲时间达到之后就会被销毁
        taskExecutor.setKeepAliveSeconds(200);
        //异步方法内部线程名称
        taskExecutor.setThreadNamePrefix("async-");
        /**
         * 当线程池的任务缓存队列已满 并且线程池中的线程数目达到 MaxPoolSize ,如果还有任务到来就采取任务拒绝策略
         * 通常有以下四种策略:
         *  ThreadPoolExecutor.AbortPolicy() 丢弃任务 并抛出 RejectedExecutionException 异常
         *  ThreadPoolExecutor.DiscardPolicy() 丢弃任务,但是不抛出异常
         *  ThreadPoolExecutor.DiscardOldestPolicy() 丢弃队列最前面的任务,然后重新尝试执行任务(重复此过程)
         *  ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务,自动重复调用 execute() 方法 直到成功
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = "redisPoolTaskExecutor")
    public ThreadPoolTaskExecutor getRedisPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池数量
        taskExecutor.setCorePoolSize(10);
        //设置线程池 维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(100);
        //设置线程 缓存队列
        taskExecutor.setQueueCapacity(50);
        //设置线程 空闲时间,当超过了核心线程数 之外的线程在空闲时间达到之后就会被销毁
        taskExecutor.setKeepAliveSeconds(200);
        //异步方法内部线程名称
        taskExecutor.setThreadNamePrefix("redis-");
        /**
         * 当线程池的任务缓存队列已满 并且线程池中的线程数目达到 MaxPoolSize ,如果还有任务到来就采取任务拒绝策略
         * 通常有以下四种策略:
         *  ThreadPoolExecutor.AbortPolicy() 丢弃任务 并抛出 RejectedExecutionException 异常
         *  ThreadPoolExecutor.DiscardPolicy() 丢弃任务,但是不抛出异常
         *  ThreadPoolExecutor.DiscardOldestPolicy() 丢弃队列最前面的任务,然后重新尝试执行任务(重复此过程)
         *  ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务,自动重复调用 execute() 方法 直到成功
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
