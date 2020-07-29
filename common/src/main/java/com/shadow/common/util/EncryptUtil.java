package com.shadow.common.util;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author cuipeng 2020/7/29 16:11
 */
public class EncryptUtil {

    /**
     * 3DES加密
     * @param channelKey 密钥
     * @param map
     */
    public static void encryptByMap(String channelKey, JSONObject map, String algorithm) {
        try {
            Set encryptSet = new HashSet();
            encryptSet.add("mobile");
            encryptSet.add("accountName");
            encryptSet.add("idCard");
            encryptSet.add("cardNo");
            encryptSet.add("accountMobile");
            encryptSet.add("servicePhone");

            Map encryptMap = new HashMap();
            for (Object key: map.keySet()) {
                if (encryptSet.contains(key)) {
                    Object valueObj = map.get(key);
                    if (valueObj != null) {
                        String value = valueObj.toString();
                        String encrypt = desEncrypt(value, channelKey, algorithm);
                        encryptMap.put(key, encrypt);
                    }
                }
            }
            map.putAll(encryptMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * MD5加密
     * @param message
     * @param charsetName
     * @return
     */
    public static String md5Encrypt(String message, String charsetName) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] md5Bytes = new byte[0];
        try {
            md5Bytes = md5.digest(message.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return ByteUtil.toHexString(md5Bytes);
    }


    /**
     * 3des加密
     * @param data 原数据
     * @param key 密钥
     * @return 加密数据
     */
    private static String desEncrypt(String data, String key, String algorithm) throws UnsupportedEncodingException {
        byte[] keyBytes = null;
        keyBytes = getBytes(key, keyBytes);
        SecretKey deskey = new SecretKeySpec(keyBytes, "DESede");
        Cipher cipher;
        byte[] bytes = null;
        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            bytes = cipher.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ByteUtil.toHexString(bytes);
    }

    private static byte[] getBytes(String key, byte[] keyBytes) {
        if (key.length() == 16) {
            keyBytes = newInstance8Key(ByteUtil.convertHexString(key));
        } else if (key.length() == 32) {
            keyBytes = newInstance16Key(ByteUtil.convertHexString(key));
        } else if (key.length() == 48) {
            keyBytes = newInstance24Key(ByteUtil.convertHexString(key));
        }
        return keyBytes;
    }

    private static byte[] newInstance24Key(byte[] key) {
        if ((key != null) && (key.length == 24)) {
            return key;
        }
        System.err.println("密钥长度有误,期望值[24]");
        return null;
    }

    private static byte[] newInstance16Key(byte[] key) {
        if ((key != null) && (key.length == 16)) {
            byte[] b = new byte[24];
            System.arraycopy(key, 0, b, 0, 16);
            System.arraycopy(key, 0, b, 16, 8);
            key = null;
            return b;
        }
        System.err.println("密钥长度有误,期望值[16]");
        return null;
    }

    private static byte[] newInstance8Key(byte[] key) {
        if ((key != null) && (key.length == 8)) {
            byte[] b = new byte[24];
            System.arraycopy(key, 0, b, 0, 8);
            System.arraycopy(key, 0, b, 8, 8);
            System.arraycopy(key, 0, b, 16, 8);
            key = null;
            return b;
        }
        System.err.println("密钥长度有误,期望值[8]");
        return null;
    }

}
