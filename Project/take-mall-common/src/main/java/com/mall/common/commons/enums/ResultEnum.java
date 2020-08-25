package com.mall.common.commons.enums;

/**
 * 通用返回枚举
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public enum ResultEnum {
    SUCCESS("10001", "操作成功"),
    FAILED("10002", "操作失败"),
    PART_SUCCESS("10003", "部分操作成功"),
    CLIENT_NOT_FIND("20001", "未获取到 client"),
    ;
    private final String code;
    private final String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
