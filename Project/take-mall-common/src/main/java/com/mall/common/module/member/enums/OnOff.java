package com.mall.common.module.member.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * 开关枚举
 */
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
