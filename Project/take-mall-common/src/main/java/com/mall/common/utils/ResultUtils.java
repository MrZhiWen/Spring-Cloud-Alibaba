package com.mall.common.utils;


import com.mall.common.commons.bean.ResultVO;
import com.mall.common.commons.enums.ResultEnum;

/**
 * 通用返回工具类
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class ResultUtils {

    public static ResultVO success() {
        return ResultVO.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(ResultEnum.SUCCESS.getMsg())
                .build();
    }

    public static ResultVO success(String msg) {
        return ResultVO.builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(msg)
                .build();
    }

    public static ResultVO success(String msg, Object data) {
        return ResultVO.builder()
                .code(ResultEnum.SUCCESS.getCode()).message(msg).data(data)
                .build();
    }

    public static ResultVO success(Object data) {
        return ResultVO.builder()
                .code(ResultEnum.SUCCESS.getCode()).message(ResultEnum.SUCCESS.getMsg()).data(data)
                .build();
    }

    public static ResultVO failed() {
        return ResultVO.builder()
                .code(ResultEnum.FAILED.getCode())
                .message(ResultEnum.FAILED.getMsg())
                .build();
    }

    public static ResultVO failed(String msg) {
        return ResultVO.builder()
                .code(ResultEnum.FAILED.getCode())
                .message(msg)
                .build();
    }

    public static ResultVO failed(String msg, Object data) {
        return ResultVO.builder()
                .code(ResultEnum.FAILED.getCode()).message(msg).data(data)
                .build();
    }

    public static ResultVO of(ResultEnum resultEnum, Object data) {
        return ResultVO.builder()
                .code(resultEnum.getCode()).message(resultEnum.getMsg()).data(data)
                .build();
    }

    public static ResultVO of(String code, String msg) {
        return ResultVO.builder()
                .code(code).message(msg)
                .build();
    }

    public static ResultVO of(String msg, Object data) {
        return ResultVO.builder()
                .code(ResultEnum.SUCCESS.getCode()).message(msg).data(data)
                .build();
    }

    public static ResultVO of(String code, String msg, Object data) {
        return ResultVO.builder()
                .code(code).message(msg).data(data)
                .build();
    }

}
