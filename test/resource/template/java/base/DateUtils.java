package ${root.basePackage}.common.util;

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
        YYYYMMDD("yyyyMMdd"),
        /**
         * 格式：2016/11/02
         */
        SPLIT_YYYY_MM_DD("yyyy/MM/dd"),
        /**
         * 格式：2016-11-02
         */
        YYYY_MM_DD("yyyy-MM-dd"),
        /**
         * 格式：2016-11-02 13:22:59
         */
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        /**
         * 格式：2016-11-02 13:22:59
         */
        YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
        /**
         * 格式：2016-11-02 星期一
         */
        YYYY_MM_DD_WEEK("yyyy-MM-dd EEE"),

        /**
         * 格式：HH:mm:ss
         */
        HH_MM_SS("HH:mm:ss"),
        /**
         * 格式：HH
         */
        HH("HH");

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
    public static Date formatNowToDate(Pattern pattern) {
        return parse(format(new Date(), pattern), pattern);
    }

    /**
     * 对时间进行格式化返回
     *
     * @param pattern
     * @return
     */
    public static Date formatToDate(Date date, Pattern pattern) {
        return parse(format(date, pattern), pattern);
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

    /**
     * 对日期进特定天数偏移
     *
     * @param date
     * @param days 为正时向后偏移，为负时向前偏移
     * @return
     */
    public static Date addDays(Date date, int days) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, days);
    }

    /**
     * 对日期进特定天数偏移
     *
     * @param date
     * @param days    为正时向后偏移，为负时向前偏移
     * @param pattern 格式化，可以完成清零逻辑
     * @return
     */
    public static Date addDays(Date date, int days, Pattern pattern) {
        return parse(format(addDays(date, days), pattern), pattern);
    }

    /**
     * 对日期进特定天数偏移, 基于现在
     *
     * @param days 为正时向后偏移，为负时向前偏移
     * @return
     */
    public static Date addDaysBaseNow(int days) {
        return addDays(new Date(), days);
    }

    /**
     * 对日期进特定天数偏移, 基于现在
     *
     * @param days 为正时向后偏移，为负时向前偏移
     * @return
     */
    public static Date addDaysBaseNow(int days, Pattern pattern) {
        return addDays(new Date(), days, pattern);
    }
}