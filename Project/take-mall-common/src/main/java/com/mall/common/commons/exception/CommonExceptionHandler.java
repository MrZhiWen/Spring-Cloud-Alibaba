package com.mall.common.commons.exception;

import com.mall.common.commons.bean.ResultVO;
import com.mall.common.commons.enums.ResultEnum;
import com.mall.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用异常处理
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LogicException.class)
    public ResultVO handleLogicException(HttpServletRequest request, HttpServletResponse response, LogicException logicException) {
        log.info("通用异常处理 ==>> 接口[{}] 参数[{}] 异常", request.getRequestURI(), request.getParameterMap(), logicException);
        ErrorMsg errorMsg = logicException.getErrorMsg();
        return ResultVO.builder().code(errorMsg.getCode()).message(errorMsg.getMsg()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex){
        String requestURI = request.getRequestURI();
        log.info("handleIllegalArgumentException requestURI:[{}]", requestURI);
        return ResultUtils.failed(ex.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    public ResultVO handleClientException(ClientException ce){
        ErrorMsg errorMsg = ce.getErrorMsg();
        String code = ResultEnum.CLIENT_NOT_FIND.getCode();
        return ResultUtils.failed(code, errorMsg.getMsg());
    }
}
