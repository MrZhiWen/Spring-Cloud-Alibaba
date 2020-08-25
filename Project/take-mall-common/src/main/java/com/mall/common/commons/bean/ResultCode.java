package com.mall.common.commons.bean;

import java.io.Serializable;

public class ResultCode implements Serializable {
    private static final long serialVersionUID = -1068205605060334245L;
    /**成功状态码*/
    public static final String SUCCESS="0000";
    /**失败状态码*/
    public static final String FAIL="9999";
    /**用户未登录*/
    public static final String USER_NOT_LOGIN="1001";
    /**领券失败*/
    public static final String COUPON_GET_FAIL="1002";




}
