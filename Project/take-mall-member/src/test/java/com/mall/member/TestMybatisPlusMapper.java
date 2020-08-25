package com.mall.member;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.module.member.bean.SysUser;
import com.mall.common.module.member.enums.OnOff;
import com.mall.common.module.member.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : TestMybatisPlusMapper.java
 * @Description : 测试 mybatis plus 操作
 * @Author : lizhiwen
 * @Date: 2020-08-24 12:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestMybatisPlusMapper {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 测试 插入 组件生成策略
     * pojo 主键设置 @TableId(value = "user_id", type = IdType.AUTO)
     * pojo 处理字段不一致 @TableField(value = "id")  不存在字段 @TableField(exist = false)
     */
    @Test
    public void testInsert() {
        SysUser sysUser = SysUser.builder().userName("张三")
                .limitedIp("127.0.0.1")
                .limitMultiLogin(OnOff.OFF)
                .loginName("zhangsan")
//                .password("123142")
                .valid("N")
                .lastChangePwdTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        int insert = sysUserMapper.insert(sysUser);
        System.out.println("insert => " + insert);
        System.out.println("sysUser id info " + sysUser.getUserId());
    }

    /**
     * 测试 主键查询
     */
    @Test
    public void testSelectById() {
        SysUser sysUser = sysUserMapper.selectById(3L);
        System.out.println("selectById result " + sysUser);
    }

    /**
     * 测试 主键更新
     */
    @Test
    public void testUpdateById() {
        SysUser sysUser = SysUser.builder().userName("李志文")
                .limitedIp("127.0.0.1")
                .limitMultiLogin(OnOff.OFF)
                .loginName("lizhiwen")
                .password("123142")
                .valid("N")
                .lastChangePwdTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .userId(3L)
                .build();
        int i = sysUserMapper.updateById(sysUser);
        System.out.println("updateById result " + i);
        System.out.println("updateById  " + sysUser);
    }

    /**
     * 测试 条件更新
     * eq //相等 user_id = 3
     * ne // 不等于 <>
     * gt //大于 >
     * ge //大于等于 >=
     * lt //小于 <
     * le //小于等于 <=
     * 详细条件说明 https://baomidou.com/guide/wrapper.html
     */
    @Test
    public void testWhereUpdate() {
        /**
         * QueryWrapper
         */
        SysUser sysUser = SysUser.builder().valid("N")
                .build();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",2); //相等 user_id = 3
//        wrapper.ne("user_id",3); //不等于 user_id <> 3
        int update = sysUserMapper.update(sysUser, wrapper);
        /**
         * UpdateWrapper
         * 数据库字段的名字
         */
//        UpdateWrapper<SysUser> wrapper1 = new UpdateWrapper<>();
//        wrapper1.set("user_name","李志文1")
//                .eq("user_id",3);
//        int update = sysUserMapper.update(null, wrapper1);

        System.out.println("update result " + update);
        System.out.println("update  " + JSONObject.toJSONString(sysUser));
    }

    /**
     * 测试 分页查询
     */
    @Test
    public void testSelectPage(){
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        Page<SysUser> page = new Page<>(1,1);
        //查询 第一页的 每页一条数据
        Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, null);
        System.out.println(JSONObject.toJSONString(sysUserPage));
    }

    /**
     * 测试 查询list
     */
    @Test
    public void testSelectList(){
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();

        //查询 第一页的 每页一条数据
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(JSONObject.toJSONString(sysUsers));
    }

    /**
     * 测试 删除数据 软删除(逻辑删除)
     */
    @Test
    public void testDelete(){

        //查询 第一页的 每页一条数据
        int i = sysUserMapper.deleteById(2);
        System.out.println("deleteById result " + i);
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(JSONObject.toJSONString(sysUsers));
    }


    /**
     * 测试 自定义查询方法
     */
    @Test
    public void testMapper(){
        SysUser sysUser = sysUserMapper.selectFindById("3");
        System.out.println(JSONObject.toJSONString(sysUser));
    }



}
