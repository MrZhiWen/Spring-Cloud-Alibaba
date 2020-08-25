package com.mall.common.commons.exception;


import com.mall.common.commons.enums.ResultEnum;

public class ClientException extends LogicException {

    public ClientException(ErrorMsg errorMsg) {
        super(errorMsg);
    }

    public ClientException(String code, String msg) {
        super(code, msg);
    }

    public ClientException(String msg){
        super(ResultEnum.CLIENT_NOT_FIND.getCode(), msg);
    }
}
