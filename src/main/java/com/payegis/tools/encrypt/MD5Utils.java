package com.payegis.tools.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Company: 北京通付盾数据科技有限公司
 * User: 陈作立
 * Date: 2018/3/12
 * Time: 16:32
 * Description: MD5加密
 * Ps: MD5 SALT
 */
public class MD5Utils {

    /**
     * Description: 32位md5
     * Param: [str]
     * Return: java.lang.String
     * Date: 2018/3/12
     * Time: 15:57
     */
    public static String strToMd5_32(String str) {
        String md5Str = "";
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                md5Str = buf.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

    /**
     * Description: 16位md5
     * Param: [str]
     * Return: java.lang.String
     * Date: 2018/3/12
     * Time: 15:58
     */
    public static String strToMd5_16(String str) {
        String md5Str = "";
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                md5Str = buf.toString().substring(8, 24);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

    /**
     * Description: 可逆加密
     * Param: [inStr]
     * Return: java.lang.String
     * Date: 2018/3/12
     * Time: 15:58
     */
    public static String encode(String inStr) {
        if (org.apache.commons.lang.StringUtils.isEmpty(inStr)) return null;
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    /**
     * Description: 加密后解密
     * Param: [inStr]
     * Return: java.lang.String
     * Date: 2018/3/12
     * Time: 15:59
     */
    public static String decode(String inStr) {
        if (org.apache.commons.lang.StringUtils.isEmpty(inStr)) return null;
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

}
