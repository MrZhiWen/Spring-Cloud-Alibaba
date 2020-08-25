package com.mall.common.commons.exception;


import com.mall.common.commons.enums.ResultEnum;

/**
 * 业务逻辑异常
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class LogicException extends RuntimeException {

    /**
     * 异常信息
     */
    private ErrorMsg errorMsg;

    public LogicException(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LogicException(String code, String msg) {
        this.errorMsg = new ErrorMsg(code, msg);
    }

    public LogicException(ResultEnum resultEnum) {
        this.errorMsg = new ErrorMsg(resultEnum.getCode(), resultEnum.getMsg());
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }
}
