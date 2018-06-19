package com.payegis.tools.string;

import org.apache.log4j.Logger;

/**
 * company: 北京通付盾数据科技有限公司
 * user: chenzuoli
 * date: 2018/6/7
 * time: 10:14
 * description: 字符转换控制工具类
 */
public class CharacterUtils {
    private static Logger logger = Logger.getLogger(CharacterUtils.class);
    private static final char DBC_CHAR_START = 33; // 半角!
    private static final char DBC_CHAR_END = 126; // 半角~
    private static final char SBC_CHAR_START = 65281; // 全角！
    private static final char SBC_CHAR_END = 65374; // 全角～
    private static final int CONVERT_STEP = 65248; // 全角半角转换间隔
    private static final char SBC_SPACE = 12288; // 全角空格 12288
    private static final char DBC_SPACE = ' '; // 半角空格
    private static final char DBC_NUMBER_START = 48; // 半角数值0
    private static final char DBC_NUMBER_END = 57; // 半角数值9
    private static final char SBC_NUMBER_START = 48 + 65248; // 全角数值0
    private static final char SBC_NUMBER_END = 57 + 65248; // 全角数值9
    private static final char SBC_CHAR_UPPERCASE_START = 65 + 65248; // 全角大写字母A
    private static final char SBC_CHAR_UPPERCASE_END = 90 + 65248; // 全角大写字母Z
    private static final char SBC_CHAR_LOWERCASE_START = 97 + 65248; // 全角小写字母a
    private static final char SBC_CHAR_LOWERCASE_END = 122 + 65248; // 全角小写字母z

    /**
     * description:  半角字符->全角字符转换,只处理空格，!到˜之间的字符，忽略其他
     * param: [src]
     * return: java.lang.String
     * date: 2018/6/7
     * time: 10:23
     */
    public static String bj2qj(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) { // 字符是!到~之间的可见字符
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * description:  全角字符->半角字符转换,只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * param: [src]
     * return: java.lang.String
     * date: 2018/6/7
     * time: 10:23
     */
    public static String qj2bj(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
                buf.append(DBC_SPACE);
            } else { // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * description: 全角转半角，只转换数字、字母
     * param: [src]
     * return: java.lang.String
     * date: 2018/6/7
     * time: 10:24
     */
    public static String qj2bjCharNumber(String src) {
        if (src == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_NUMBER_START && ca[i] <= SBC_NUMBER_END) { // 如果位于全角0-9区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] >= SBC_CHAR_LOWERCASE_START && ca[i] <= SBC_CHAR_LOWERCASE_END) {
                buf.append((char)(ca[i] - CONVERT_STEP));
            } else if (ca[i] >= SBC_CHAR_UPPERCASE_START && ca[i] <= SBC_CHAR_UPPERCASE_END) {
                buf.append((char)(ca[i] - CONVERT_STEP));
            } else {
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

}
