package com.mall.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 时间日期相关工具类
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
public class DateTimeUtils {

    public static LocalDateTime tran2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
}
