package com.mall.common.commons.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ResultVO implements Serializable {
    private String code;
    private String message;
    private Object data;

    public ResultVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO() {
    }

    public ResultVO(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultVO success(String message) {
        return new ResultVO(ResultCode.SUCCESS, message);
    }

    public static ResultVO success(String message, Object data) {
        return new ResultVO(ResultCode.SUCCESS, message, data);
    }

    public static ResultVO fail(String message) {
        return new ResultVO(ResultCode.FAIL, message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
