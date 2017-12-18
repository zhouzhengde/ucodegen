package com.scsxyz.java.generator.util;

import java.util.regex.Pattern;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public final class StringUtils {

    /**
     * 将一个名称转换成驼峰命名方式
     *
     * @param name
     * @return
     */
    public static String transformCamel(String name) {
        char[] chars = name.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean raise = false;
        for (int i = 0; i < chars.length; i++) {
            Pattern pattern = Pattern.compile("([A-Za-z]|[0-9])+");
            String t = chars[i] + "";
            if (i == 0) {
                t = t.toLowerCase();
            }
            if (raise) {
                t = t.toUpperCase();
            }
            boolean matches = pattern.matcher(t).matches();
            if (matches) {
                sb.append(t);
                raise = false;
            }
            pattern = Pattern.compile("-|_");
            matches = pattern.matcher(t).matches();
            if (matches) {
                raise = true;
            }
        }
        return sb.toString();
    }
}
