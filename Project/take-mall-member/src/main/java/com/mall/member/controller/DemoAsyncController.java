package com.mall.member.controller;


import com.mall.common.module.member.bean.SysUser;
import com.mall.common.module.member.enums.OnOff;
import com.mall.member.service.DemoAsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : DemoAsyncController.java
 * @Description : spring boot 异步处理案例 控制类
 * @Author : lizhiwen
 * @Date: 2020-08-20 16:03
 */
@RestController
@RequestMapping("/async")
@Slf4j
@Api(value = "异步任务demo相关接口", tags = {"异步任务demo"})
public class DemoAsyncController {

    @Autowired
    private DemoAsyncService demoAsyncService;

    /**
     * 使用默认线程池
     * @return
     */
    @ApiOperation(value = "用户注册 添加积分", notes = "提示内容")
    @GetMapping("/addUser")
    public SysUser registerUser(){
        SysUser sysUser = SysUser.builder().userName("李志文")
                .limitedIp("123")
                .limitMultiLogin(OnOff.OFF)
                .loginName("lizhiwen")
                .build();
        log.info("获取用户信息{}",sysUser);
        demoAsyncService.addScore();
        log.info("-----用户注册成功------");
        return sysUser;
    }
    /**
     * 使用指定线程池
     * @return
     */
    @ApiOperation(value = "用户注册,添加积分,并且更新缓存", notes = "提示内容")
    @GetMapping("/addUser1")
    public SysUser registerUser1(){
        SysUser sysUser = SysUser.builder().userName("李志文")
                .limitedIp("123")
                .limitMultiLogin(OnOff.OFF)
                .loginName("lizhiwen")
                .build();
        log.info("获取用户信息{}",sysUser);
        demoAsyncService.addScore1();
        demoAsyncService.addScore2();
        log.info("-----用户注册成功------");
        return sysUser;
    }


}
