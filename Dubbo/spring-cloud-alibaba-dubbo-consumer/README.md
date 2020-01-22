    ##spring-cloud-alibaba-dubbo-consumer##

        参考：
            Nacos Spring Cloud 快速开始 :
                https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html
            
            Spring Cloud ribbon :
                https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
                                           
        服务消费者
        1、踩坑点
            在使用 RestTemplate 调用服务提供者 是 经常会遇见 400 null的错误。原因是 服务提供者 客户端注解问题
            应该使用 @RequestBody 注解
            //注意次调用方式 在客户端注解一定要 使用 @RequestBody 注解
         
        
         目前架构 使用的是Spring Cloud 的 RestTemplate方式调用服务的
                  可以使用 Feign 方式调用服务
         负载均衡 Ribbon 集成进来实现集群负载。
         
           Nacos 注册中心
                 需注意 bootstrap.yaml 文件中配置  discovery:
                                                   server-addr: localhost:8848 配置注册中心地址
           
           Nacos 配置中心
                 需要注意 bootstrap.yaml 文件中配置  
                               spring:
                                cloud:
                                    nacos:
                                      config:
                                        server-addr: localhost:8848  #配置配置中心地址
                                        file-extension: yaml        #配置文件类型 
                                        namespace: d8440cb7-6a1c-44e9-b244-21acdd53b653  #空间名称
                 
                 可以将 数据源和基础配置添加其中
                 
                    Nacos后台配置 如下:
                            server:
                              port: 12301   #端口
                            spring:
                              application:
                                name: spring-cloud-alibaba-consumer # 名称
                              cloud:
                                nacos:
                                  discovery:
                                    server-addr: localhost:8848         #配置注册中心地址
                                    namespace: 95d3a1d6-4df6-4814-b049-dd3737ca131c #服务管理空间
                                    
                 多个环境可以 创建多个空间来隔离区分
                 
         ###集成 Dubbo 协议调用###