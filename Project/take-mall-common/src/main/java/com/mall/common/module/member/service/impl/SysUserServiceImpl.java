package com.mall.common.module.member.service.impl;

import com.mall.common.module.member.bean.SysUser;
import com.mall.common.module.member.mapper.SysUserMapper;
import com.mall.common.module.member.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author lizhiwen
 * @since 2020-08-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
