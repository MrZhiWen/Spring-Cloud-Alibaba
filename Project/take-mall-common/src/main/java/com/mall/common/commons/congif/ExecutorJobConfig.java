package com.mall.common.commons.congif;//package com.mall.common.common.config;
//
//import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.commons.util.InetUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 分布式定时任务执行器配置
// *
// * @author lizhiwen
// * @since 2020/8/23 23:43 下午
// */
//@Slf4j
//@Configuration
//// @EnableConfigurationProperties(XxlJobProperties.class)
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class ExecutorJobConfig {
//
//    private final XxlJobProperties xxlJobProps;
//    private final InetUtils inetUtils;
//
//    @Bean
//    public XxlJobSpringExecutor xxlJobExecutor() {
//        log.info("xxlJob 线程池 ==>> 初始化");
//        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
//        xxlJobSpringExecutor.setAdminAddresses(xxlJobProps.getAdmin().getAddress());
//        xxlJobSpringExecutor.setAppName(xxlJobProps.getExecutor().getAppName());
//        String ip = xxlJobProps.getExecutor().getIp();
//        if (StringUtils.isBlank(ip)) {
//            ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
//        }
//        xxlJobSpringExecutor.setIp(ip);
//        xxlJobSpringExecutor.setPort(xxlJobProps.getExecutor().getPort());
//        xxlJobSpringExecutor.setAccessToken(xxlJobProps.getAccessToken());
//        xxlJobSpringExecutor.setLogPath(xxlJobProps.getExecutor().getLogPath());
//        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProps.getExecutor().getLogRetentionDays());
//        log.info("xxlJob 线程池 ==>> 初始化完成 ip[{}] adminUrl[{}] appName[{}]", ip, xxlJobProps.getAdmin().getAddress(),xxlJobProps.getExecutor().getAppName());
//        return xxlJobSpringExecutor;
//    }
//}