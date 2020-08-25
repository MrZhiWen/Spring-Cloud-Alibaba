package com.mall.common.utils;


import com.mall.common.commons.constant.ConstantRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 订单相关工具类
 *
 * @author lizhiwen
 * @since 2020/8/23 23:43 下午
 */
@Component
public class OrderUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 设置6自增6位，用于补全操作
     */
    private static final String STR_FORMAT = "000000";


    /**
     * redis流水号自增
     *
     * @param key      自己设置，保存当前自增值
     * @param liveTime 在redis中的缓存时间，方法中设置单位(秒/分/天等)
     * @return 自增序列
     */
    public String incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        long increment = entityIdCounter.incrementAndGet();

        /**
         * 初始设置过期时间
         * iveTime 过期时间；TimeUnit.DAYS 过期时间单位, 目前默认为天
         */
        if (increment == 1 && liveTime > 0) {//
            entityIdCounter.expire(liveTime, TimeUnit.DAYS);
        }
        if (increment > 999999) {
            increment = 1L;
        }
        // 位数不够，前面补0
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(increment);
    }

    /**
     * 生成 warehouse 内部订单号
     *
     * @param prefix 按日区分订单号
     * @return 订单编号
     */
    public String genOrderNum(String prefix) {
        StringBuilder numBuffer = new StringBuilder();
        numBuffer.append(prefix);
        LocalDateTime now = LocalDateTime.now();
        String midKey = now.format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.CHINA));
        numBuffer.append(midKey);
        String incKey = ConstantRedisKey.ORDER_DAY_NUM + midKey;
        String num = incr(incKey, 1);
        numBuffer.append(num);
        return numBuffer.toString();
    }
}
