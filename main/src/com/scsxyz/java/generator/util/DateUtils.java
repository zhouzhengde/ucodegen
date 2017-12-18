/*
 * Copyright (c) 2016. Bond(China)
 */

package com.scsxyz.java.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期的操作工具集锦
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public final class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {

    }

    /**
     * 日期格式
     *
     * @author Bond(China)
     */
    public enum Pattern {

        /**
         * 格式:20161102
         */
        yyyyMMdd("yyyyMMdd"),
        /**
         * 格式：2016/11/02
         */
        split_yyyy_MM_dd("yyyy/MM/dd"),
        /**
         * 格式：2016-11-02
         */
        yyyy_MM_dd("yyyy-MM-dd"),
        /**
         * 格式：2016-11-02 13:22:59
         */
        yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
        /**
         * 格式：2016-11-02 星期一
         */
        yyyy_MM_dd_week("yyyy-MM-dd EEE"),

        /**
         * 格式：HH:mm:ss
         */
        HH_mm_ss("HH:mm:ss");

        /**
         * pattern value
         */
        private String value;

        Pattern(String value) {
            this.value = value;
        }
    }

    /**
     * String to Date by pattern
     *
     * @param date    Date对象
     * @param pattern 格式化的模式
     * @return Date
     */
    public static Date parse(String date, String pattern) {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            LOGGER.warn("[DateUtils parse]", e);
            return null;
        }
    }

    /**
     * Date to String by pattern
     *
     * @param date    Date对象
     * @param pattern 格式化的模式
     * @return Date
     */
    public static String format(Date date, String pattern) {

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.format(date);
        } catch (Exception e) {
            LOGGER.warn("[DateUtils format]", e);
            return null;
        }
    }

    /**
     * String to Date by pattern
     *
     * @param date    Date对象
     * @param pattern 格式化的模式
     * @return Date
     */
    public static Date parse(String date, Pattern pattern) {

        return parse(date, pattern.value);
    }

    /**
     * Date to String by pattern
     *
     * @param date    Date对象
     * @param pattern 格式化的模式
     * @return Date
     */
    public static String format(Date date, Pattern pattern) {

        return format(date, pattern.value);
    }

    /**
     * 对现在的时间进行格式化返回
     *
     * @param pattern
     * @return
     */
    public static String formatNow(Pattern pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 对现在的时间进行格式化返回
     *
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return format(new Date(), pattern);
    }
}
