package com.mall.common.commons.exception;


import com.mall.common.commons.enums.ResultEnum;

/**
 * 订单模块相关业务异常
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class OrderException extends LogicException {

    public OrderException(ErrorMsg errorMsg) {
        super(errorMsg);
    }

    public OrderException(String code, String msg) {
        super(code, msg);
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum);
    }
}
