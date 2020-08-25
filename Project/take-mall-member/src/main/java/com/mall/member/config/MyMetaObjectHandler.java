package com.mall.member.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mall.common.module.member.bean.SysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * mybatis plus 公用字段填充
 */
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