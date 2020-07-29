package com.shadow.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * @author cuipeng 2020/7/29 16:10
 */
@Slf4j
public class SignUtil {

    /**
     * 签名
     * @param channelKey 密钥
     * @return
     */
    public static String signByMap(String channelKey, TreeMap<String, Object> map) throws Exception {
        return signByMap(channelKey, map, "UTF-8");
    }

    private static String signByMap(String channelKey, TreeMap<String, Object> map, String charsetName) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = map.keySet().iterator();
            sb.append(channelKey);
            List ingnoreList = new ArrayList();
            ingnoreList.add("imageList");
            ingnoreList.add("sign");

            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object valueObj = map.get(key);
                if (valueObj != null && !isIgnore(key.toString(), ingnoreList)) {
                    //并将获取的值进行拼接
                    String value = valueObj.toString();
                    sb.append(value);
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("SignData orig Data : " + sb.toString());
            }
            String signData = EncryptUtil.md5Encrypt(sb.toString(), charsetName);
            if (log.isDebugEnabled()) {
                log.debug("SignData : " + signData);
            }
            return signData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 忽略
     */
    private static boolean isIgnore(String key, List<String> ignoreList) {
        if ((null != ignoreList) && !ignoreList.isEmpty()) {
            for (String ignore : ignoreList) {
                if (key.equalsIgnoreCase(ignore)) {
                    return true;
                }
            }
        }
        return false;
    }
}
