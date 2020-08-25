package com.mall.common.module.member.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.mall.common.module.member.enums.OnOff;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author lizhiwen
 * @since 2020-08-24
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUser对象", description = "系统用户表")
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "登录密码")
    private String password;


    @ApiModelProperty(value = "软删除标识，Y/N")
    /**
     * 逻辑删除  Y 删除  N 没删除
     */
    @TableLogic
    private String valid;

    @ApiModelProperty(value = "限制允许登录的IP集合")
    private String limitedIp;

    @ApiModelProperty(value = "账号失效时间，超过时间将不能登录系统")
    private LocalDateTime expiredTime;

    @ApiModelProperty(value = "最近修改密码时间，超出时间间隔，提示用户修改密码")
    private LocalDateTime lastChangePwdTime;

    @ApiModelProperty(value = "是否允许账号同一个时刻多人在线，Y/N")
    private OnOff limitMultiLogin;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
