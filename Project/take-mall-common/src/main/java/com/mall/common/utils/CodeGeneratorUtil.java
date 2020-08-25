package com.mall.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李志文
 * @Title: CodeGeneratorUtil
 * @ProjectName newyhcloud
 * @Description: 代码生成器（Oracle生成后实体需自己手动加入@KeySequence注解标识序列）
 * @date 创建于2019-1-23 15:57
 */
public class CodeGeneratorUtil {

    /**
     * @param systemName 系统名称 例：newyhcloud-service-platform
     * @param idType     主键生成策略  例：IdType.INPUT
     * @param author     作者 例：李志文
     * @param dbType     数据库类型 例：DbType.ORACLE
     * @param dbUrl      数据库  例：jdbc:oracle:thin:@(description=(address_list= (address=(host=10.0.9.11) (protocol=tcp)(port=1521))(address=(host=10.0.9.12)(protocol=tcp) (port=1521)) (load_balance=yes)(failover=yes))(connect_data=(service_name= hrdev)))
     * @param driverName 驱动名 例：oracle.jdbc.driver.OracleDriver
     * @param dbName     数据库名  例：hrdev
     * @param dbPass     数据库密码  例：hrdev
     * @param moduleName 模块名  例：platform
     * @param parent     模块名所生成的父目录  例：com.yihuacomputer.newyhcloud.server
     * @param tableName  映射的数据库表名  例：PUB_NEW_USER
     */
    public static void codeGeneration(String systemName, IdType idType, String author, DbType dbType, String dbUrl, String driverName, String dbName, String dbPass, String moduleName, String parent, String tableName) {

        if (StringUtils.isEmpty(systemName) || idType == null || StringUtils.isEmpty(author) || dbType == null ||
                StringUtils.isEmpty(dbUrl) || StringUtils.isEmpty(driverName) || StringUtils.isEmpty(dbName) ||
                StringUtils.isEmpty(dbPass) || StringUtils.isEmpty(moduleName) || StringUtils.isEmpty(parent) ||
                StringUtils.isEmpty(tableName)) {
            throw new MybatisPlusException("请求参数不能为空！");
        }

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setActiveRecord(true); // 是否支持AR模式
        String projectPath = System.getProperty("user.dir") + "/" + systemName;
        gc.setOutputDir(projectPath + "/src/main/java"); // 生成路径
        gc.setFileOverride(true); // 文件覆盖
        gc.setIdType(idType); // 主键策略--Oracle使用Sequence
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setBaseResultMap(true); //生成基本的resultMap
        gc.setBaseColumnList(true); //生成基本的SQL片段
        gc.setAuthor(author); // 作者
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // dsc.setSchemaName("public");
        dsc.setDbType(dbType);// 设置数据库类型
        dsc.setUrl(dbUrl);
        dsc.setDriverName(driverName);
        dsc.setUsername(dbName);
        dsc.setPassword(dbPass);
        mpg.setDataSource(dsc);

        // 3、包名策略配置
        PackageConfig pc = new PackageConfig();
        pc.setService("service");
        pc.setController("rest");
        pc.setEntity("bean");
        pc.setModuleName(moduleName);
        pc.setParent(parent);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // templateConfig.setEntity();
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false); // 全局大写命名 ORACLE 注意
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_"); // 此处可以修改为您的表前缀(表名以什么开头)

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void main(String[] args) {
        /**
         * 项目路径
         */
        String systemName= "take-mall-common";
        /**
         * 作者
         */
        String author = "lizhiwen";
        /**
         * 策略类型
         */
        IdType idType =IdType.AUTO;
        DbType dbType = DbType.MYSQL;
        /**
         * 数据库配置
         */
        String dbUrl = "jdbc:mysql://cdb-gi4isu4q.bj.tencentcdb.com:10061/mall?useConfigs=maxPerformance&useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dbName = "root";
        String dbPass = "qq2560500";
        //程序模块
        String moduleName = "member";
        //包路径
        String parent = "com.mall.common.module";
        //数据库表
        String tableName = "sys_user";

        CodeGeneratorUtil.codeGeneration(systemName, idType, author, dbType, dbUrl,driverName, dbName, dbPass, moduleName, parent,
                tableName);

    }
}