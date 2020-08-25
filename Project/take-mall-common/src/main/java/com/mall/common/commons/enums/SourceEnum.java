package com.mall.common.commons.enums;

/**
 * 订单来源入口
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public enum SourceEnum {
    APP("APP", "APP"),
    WM("WM", "外卖"),
    OMS("OMS", "OMS"),
    DDZT("DDZT", "订单中台"),
    ;
    private String code;
    private String desc;

    SourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
