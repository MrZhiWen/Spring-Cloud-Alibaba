package com.mall.common.commons.exception;

import java.io.Serializable;

/**
 * 通用消息体
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class ErrorMsg implements Serializable {

    private String code;
    private String msg;

    public ErrorMsg() {
    }

    public ErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
