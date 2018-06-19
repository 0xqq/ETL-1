package com.payegis.tools.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public abstract class Coder {
    private static final String KEY_SHA = "SHA";
    private static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     */
    private static final String KEY_MAC = "HmacMD5";

    /**
     * description: BASE64解密
     * param: [key]
     * return: byte[]
     * date: 2018/6/13
     * time: 15:18
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * description:  BASE64加密
     * param: [key]
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:18
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * description: MD5加密
     * param: [data]
     * return: byte[]
     * date: 2018/6/13
     * time: 15:19
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * description: SHA加密
     * param: [data]
     * return: byte[]
     * date: 2018/6/13
     * time: 15:19
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();

    }

    /**
     * description: 初始化HMAC密钥
     * param: []
     * return: java.lang.String
     * date: 2018/6/13
     * time: 15:19
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * description: HMAC加密
     * param: [data, key]
     * return: byte[]
     * date: 2018/6/13
     * time: 15:19
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);

    }
}
