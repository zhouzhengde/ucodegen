package ${root.basePackage}.common.util;

import ${root.basePackage}.common.exception.ServiceException;
import ${root.basePackage}.common.exception.ExceptionCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 常用的操作工具集锦
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public final class Helper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);

    private Helper() {
    }

    /**
     * 判断是否为空
     *
     * @param object 需要被判定的对象
     * @return Boolean
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(String object) {
        return StringUtils.isEmpty(object);
    }

    /**
     * 判断是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(List object) {
        return CollectionUtils.isEmpty(object);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Integer v1, Integer v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Byte v1, Byte v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Long v1, Long v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1
     * @param v2
     * @return Boolean
     */

    public static boolean isEqual(Boolean v1, Boolean v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(String v1, String v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    public static boolean isTrue(Boolean val) {

        if (isNull(val) || val == false) {
            return false;
        }
        return true;
    }


    /**
     * 非0均为true, 0 为false
     *
     * @param integer
     * @return
     */
    public static Boolean toBool(Integer integer) {
        if (Helper.isNull(integer)) {
            return null;
        }
        if (Helper.isEqual(integer, 0)) {
            return false;
        }
        return true;
    }

    /**
     * "false" To boolean false, "true" to boolean true, other of null
     *
     * @param str
     * @return
     */
    public static Boolean toBool(String str) {
        if (Helper.isNull(str)) {
            return null;
        }
        if (Helper.isEqual(str, "true")) {
            return true;
        }
        if (Helper.isEqual(str, "false")) {
            return false;
        }
        return null;
    }

    /**
     * 判断数组是否为空
     *
     * @param ignoreFields
     * @return
     */
    public static boolean isEmpty(Object[] ignoreFields) {
        return ArrayUtils.isEmpty(ignoreFields);
    }


    /**
     * URL 转码
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Url encode", e);
            return URLEncoder.encode(url);
        }
    }

    /**
     * URL 转码
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        try {
            return URLDecoder.decode(url, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Url decode", e);
            return URLDecoder.decode(url);
        }
    }

    /**
     * 判断对象是否为NULL
     *
     * @param o
     * @throws ServiceException
     */
    public static void assertIsNull(Object o) throws ServiceException {
        if (Helper.isNull(o)) {
            throw new ServiceException(ExceptionCode.REQUIRED_NOT_NULL);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o
     * @throws ServiceException
     */
    public static void assertIsNull(String o) throws ServiceException {
        if (Helper.isEmpty(o)) {
            throw new ServiceException(ExceptionCode.REQUIRED_NOT_NULL);
        }
    }

    /**
     * 判断字符串是否是数组
     *
     * @param o1
     * @throws ServiceException
     */
    public static boolean isJSON(String o1) throws ServiceException {
        try {
            if (o1.startsWith("[")) {
                JSONArray.fromObject(o1);
            } else {
                JSONObject.fromObject(o1);
            }
            return true;
        } catch (Exception e) {
            LOGGER.warn("Helper.isJSON", e);
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o1
     * @param o2
     * @throws ServiceException
     */
    public static void assertEquals(String o1, String o2) throws ServiceException {
        if (Helper.isEqual(o1, o2)) {
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o1
     * @param o2
     * @throws ServiceException
     */
    public static void assertEquals(Integer o1, Integer o2) throws ServiceException {
        if (Helper.isEqual(o1, o2)) {
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
    }

    /**
     * 将数组转换成字符串
     *
     * @param arr
     * @return
     */
    public static String toString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 将StringList数组转成传统Array
     *
     * @param list
     * @return
     */
    public static String[] toArray(List<String> list) {
        if (Helper.isEmpty(list)) {
            return new String[0];
        }
        String[] rs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rs[i] = list.get(i);
        }
        return rs;
    }

    /**
     * 生成一个nonce
     *
     * @param len
     * @return
     */
    public static String nonce(int len) {
        int tlen = len;
        if (tlen > 64) {
            tlen = 64;
        }
        return SHA1.encrypt(UUID.randomUUID().toString()).substring(0, tlen);
    }

    /**
     * 生成hashCode
     *
     * @param params
     * @return
     */
    public static int generateHashCode(String[] params) {

        if (Helper.isEmpty(params)) {
            return (int) (Math.random() * 10000);
        }

        int code = 10000;
        for (int i = 0; i < params.length; i++) {
            char[] chars = params[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                code = code + chars[j] << 3 + chars[j] >> 2;
            }
        }
        return code;
    }


    /**
     * 截取
     *
     * @param str
     * @param len
     * @return
     */
    public static String trim(String str, int len) {

        if (Helper.isEmpty(str)) {
            return str;
        }

        if (str.length() > len) {
            return str.substring(0, len);
        } else {
            return str;
        }
    }

    /**
     * 交集扩展操作, 将collection2中的元素的不为NULL的值覆盖到collection1中
     *
     * @param collection1
     * @param collection2
     * @param clz
     * @param ignores
     * @param <T>
     * @return
     * @throws ServiceException
     */
    public static <T> List<T> intersection(List<T> collection1, List<T> collection2, String[] ignores, Class<T> clz) throws ServiceException {

        List<T> intersection = (List<T>) CollectionUtils.intersection(collection1, collection2);

        if (CollectionUtils.isEmpty(intersection)) {
            return intersection;
        }

        List<T> result = new ArrayList();
        for (int i = 0; i < intersection.size(); i++) {
            final T old = intersection.get(i);
            T main = (T) CollectionUtils.find(collection2, new Predicate() {
                @Override
                public boolean evaluate(Object o) {
                    return old.equals(o);
                }
            });
            result.add(Helper.merge(old, main, ignores, clz));
        }
        return result;
    }

    /**
     * 获取汉字的拼音
     *
     * @param src
     * @return
     */
    public static String toPinYin(String src) {

        if (Helper.isEmpty(src)) {
            return "";
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuilder rs = new StringBuilder();
        char[] chs = src.toCharArray();
        try {
            for (int i = 0; i < chs.length; i++) {
                if (!Validator.CHINESE.match(chs[i] + "")) {
                    rs.append(chs[i]);
                    continue;
                }
                String[] tmp = PinyinHelper.toHanyuPinyinStringArray(chs[i], format);
                rs.append(tmp[0]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            LOGGER.error("Convert PinYin Error", e);
        }
        return rs.toString();
    }

    /**
     * 将一串字串转换成Int, 累加字符的ASCII值，按位乘以2的指数
     *
     * @param src
     * @return
     */
    public static int toIntForCompare(String src) {
        if (Helper.isEmpty(src)) {
            return 0;
        }
        String tmp = Helper.toPinYin(src).toUpperCase();
        int limit = 5;
        if (tmp.length() > limit) {
            tmp = tmp.substring(0, limit);
        }
        // 补空
        if (tmp.length() < limit) {
            int needChs = limit - tmp.length();
            for (int i = 0; i < needChs; i++) {
                tmp += "A";
            }
        }
        char[] chs = tmp.toCharArray();
        int rs = 0;
        int size = chs.length;
        for (int i = chs.length - 1; i >= 0; i--) {
            int it = chs[i];
            rs += it * Math.pow(10, size - i);
        }
        return rs;
    }

    /**
     * 将o2中不为NULL的值 且值与o1的值不相等，全部赋于o1， 两个类应属于同一个类, 最终返回o1
     *
     * @param o1
     * @param o2
     */
    public static <T> T merge(T o1, T o2, String[] ignoreFields, Class<T> clz) throws ServiceException {
        try {
            T result = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = null;
                if (isIgnore(ignoreFields, field)) {
                    value = field.get(o1);
                } else {
                    value = field.get(o2);
                }
                field.set(result, value);
                field.setAccessible(false);
                field.setAccessible(false);
            }
            return result;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR, e);
        }
    }

    private static boolean isIgnore(String[] ignoreFields, Field field) {
        boolean ignore = false;
        if (Helper.isEmpty(ignoreFields)) {
            return ignore;
        }
        for (int e = 0; e < ignoreFields.length; e++) {
            if (Helper.isEqual(field.getName(), ignoreFields[e])) {
                ignore = true;
            }
        }
        return ignore;
    }


    /**
     * 校验
     */
    public static enum Validator {

        ALPHA_CHINESE("([\\u4e00-\\u9fa5]|[A-Za-z])+"),
        EMOJI("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]"),
        PHONE("[1][34578][0-9]{9}"),
        EMAIL("(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+"),
        CHINESE("[\\u4e00-\\u9fa5]+");

        private String reg;

        private Validator(String reg) {
            this.reg = reg;
        }

        public boolean match(String val) {
            Pattern pattern = Pattern.compile(this.reg);
            return pattern.matcher(val).matches();
        }

        public String match(String val, String replace) {
            if (Helper.isNull(val)) {
                return val;
            }
            Pattern pattern = Pattern.compile(this.reg);
            if (Helper.isEmpty(replace)) {
                pattern.matcher(val).replaceAll("[表情]");
            }
            return pattern.matcher(val).replaceAll(replace);
        }

        public static boolean customMatch(String reg, String val) {
            Pattern pattern = Pattern.compile(reg);
            return pattern.matcher(val).matches();
        }
    }
}