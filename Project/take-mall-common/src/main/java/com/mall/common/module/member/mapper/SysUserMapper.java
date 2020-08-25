package com.mall.common.module.member.mapper;

import com.mall.common.module.member.bean.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author lizhiwen
 * @since 2020-08-24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectFindById(@Param("id") String id);

    List<SysUser> getSysUserList(String name);

}
