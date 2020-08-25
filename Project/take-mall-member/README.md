
### 会员服务
```
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(16)  NOT NULL AUTO_INCREMENT  COMMENT '用户id',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '软删除标识，Y/N',
  `limited_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `expired_time` datetime NULL DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `last_change_pwd_time` datetime NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `limit_multi_login` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ( 1,'sa', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-07-19 16:36:03', '2019-09-17 12:00:36');
INSERT INTO `sys_user` VALUES ( 2,'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-07-19 16:36:03', '2019-09-12 16:14:28');
 ```

### Nacos 配置说明



### mybatis-plus 配置说明及使用
#### 1,mybatis-plus 配置
```
# mybatis plus 配置
   mybatis-plus:
     mapper-locations: classpath*:/mapper/**/*Mapper.xml
     #实体扫描，多个package用逗号或者分号分隔
     typeAliasesPackage: com.mall.common.module.**.bean
     typeEnumsPackage: com.mall.common.module.**.enums
     type-aliases-super-type: java.lang.Object
     # 原生配置
     configuration:
       # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用
   #    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
       # 驼峰下划线转换
       map-underscore-to-camel-case: true
       # 配置的缓存的全局开关
       cache-enabled: false
       # 延时加载的开关
       lazy-loading-enabled: true
       # 开启的话，延时加载一个属性时会加载该对象全部属性，否则按需加载属性
       multiple-result-sets-enabled: true
       use-generated-keys: true
       default-statement-timeout: 60
       default-fetch-size: 100
     global-config:
       # 数据库相关配置
       db-config:
         #主键类型  AUTO:"数据库ID自增", INPUT:"用户输入ID",ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID";
         id-type: auto
         #字段策略 IGNORED:"忽略判断",NOT_NULL:"非 NULL 判断"),NOT_EMPTY:"非空判断"
         field-strategy: NOT_NULL
         #驼峰下划线转换
         column-underline: true
         #数据库大写下划线转换
         #capital-mode: true
         #逻辑删除配置
   #      logic-delete-value: 0
   #      logic-not-delete-value: 1
         db-type: mysql
         #刷新mapper 调试神器
         refresh: true
```
##### 2, 分页配置器 MybatisPlusConfig.java

#### 2,mybatis-plus 的使用
##### 1, CRUD 操作 详情请查看 TestMybatisPlusMapper.java 案例
##### 2, 自动填充
```
1,设置字段 填充
    pojo 设置字段 select 时 不被查询出来 @TableField(select = false)
    pojo 设置字段 insert 时 自动添加数据 @TableField(fill = FieldFill.INSERT)
2,配置填充方法
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 新增自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();
        if(originalObject instanceof SysUser){
            Object password = getFieldValByName("password", metaObject);
            if (password ==null){
                this.setFieldValByName("password", "123456", metaObject);
            }
        }
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
    }

    // 更新后自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
```
##### 3, 逻辑删除 
```
1,添加配置 application-local.yml mybatis plus 配置
mybatis-plus:
 global-config:
    # 数据库相关配置
    db-config:
      logic-delete-value: N ##未删除
      logic-not-delete-value: Y ## 未删除

2,pojo 字段上增加 逻辑删除 注解  @TableLogic 

3,测试  TestMybatisPlusMapper.java -> testDelete()
```
##### 4, 通用枚举
```
1,创建枚举 
    public enum OnOff implements IEnum<String> {
        OFF("Y","开启"),
        ON("N","禁用")
        ;
        private String code;
        private String name;
        OnOff(String code, String name) {
            this.code = code;
            this.name = name;
        }
        public String getCode() {
            return code;
        }
        public String getName() {
            return name;
        }
        @Override
        public String getValue() {
            return this.code;
        }
    }

2, pojo 实体对象中 新增字段 类型为枚举类型
    private OnOff limitMultiLogin;

3, 测试使用 TestMybatisPlusMapper.java -> testInsert() testSelectList()
```
### swagger2 文档工具说明
```
文档访问地址:http:IP:post/swagger-ui.html
```
#### 1, pom.xml导入依赖jar包
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.9.2</version>
    </dependency>
    
#### 2, 配置Swagger2 到IOC容器中 `案例:SwaggerConfig.java`
```
application.yaml 配置
spring:
    swagger:
        enabled: true ## 是用于区别于生产和测试环境是否开启api文档
        basePackage: com.mall.member 
        title: 会员服务,接口文档
代码如下:
/**
 * @ClassName : SwaggerConfig.java
 * @Description : Swagger2 api 文档生成工具配置
 * @Author : lizhiwen
 * @Date: 2020-08-21 08:27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 各环境之间开启 禁用api文档
     */
    @Value("${spring.swagger.enabled}")
    private Boolean swaggerEnabled;
    /**
     * 扫描接口 包路径
     */
    @Value("${spring.swagger.basePackage}")
    private String basePackage;
    /**
     * 接口文档  title
     */
    @Value("${spring.swagger.title}")
    private String title;
    /**
     * 项目名称
     */
    @Value("${spring.application.name}")
    private String name;


    /**
     * 创建 Docket
     * 用于配置swagger2，包含文档基本信息
     * 指定swagger2的作用域（这里指定包路径下的所有API）
     * @return
     */
    @Bean
    public Docket createResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(name)
//                //团队地址
//                .termsOfServiceUrl("")
                // 设置联系方式
                .contact(new Contact("lizhiwen", "", "719272090@qq.com"))
                .version("1.0")
                .build();
    }
}
```
     
#### 3, Swagger2 注解配置 解释
|               | Swagger2 常用注解             |  | |
| ------------ | ------------- | -----------    | -----------       |
| 注解          |  用途          | 注解位置        |  参数方法          |                                                                                                                         
| @Api         | 表示标识这个类是swagger的资源                |  用于类   | @Api(value = "用户相关接口",tags = {"用户操作接口"})  |
| @ApiOperation| 表示一个http请求的操作                      |  用于方法  | @ApiOperation(value="根据用户ID获取用户信息",notes="提示内容") |
| @ApiParam()  | 表示对参数的添加元数据（说明或是否必填等）,不方便使用建议使用@ApiImplicitParams      |  用于方法  | @ApiParam(name = "userId",value = "用户ID",required = true) Long id ,@ApiParam(name = "userName",value = "用户姓名" |
| @ApiImplicitParams() | 表示对参数的添加元数据（说明或是否必填等） |  用于方法    | @ApiImplicitParams({ 填写 @ApiImplicitParam 注解}) |
| @ApiImplicitParam()  | 表示对参数的添加元数据（说明或是否必填等） |  用于方法    | @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Long", required = true) |
| @ApiModel()  | 表示对类进行说明，用于参数用实体类接收         |  用于类    | @ApiModel(value = "ResultVO 对象",description = "请求返回结果") |
| @ApiModelProperty() | 表示对model属性的说明或者数据操作更改  |  用于方法   | @ApiModelProperty(value = "请求返回状态码",name = "code",example = "0000") |

