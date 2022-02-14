package org.link.core.utils;

import java.util.Objects;

/**
 * StringUtils
 * @author gol
 */
public class StringUtils {

    /**
     * 字符串拼接
     *
     * @param strings 可变字符串
     * @return java.lang.String
     */
    public static String join(String... strings) {
        StringBuilder joinBuilder = new StringBuilder();
        for (String str : strings) {
            if (Objects.nonNull(str)) {
                joinBuilder.append(str);
            }
        }
        return joinBuilder.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs 字符串
     * @return boolean
     */
    public static boolean isNotBlank(String cs) {
        return !isBlank(cs);
    }

    /**
     * 判断字符串是否为空
     *
     * @param cs 字符串
     * @return boolean
     */
    public static boolean isBlank(String cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
