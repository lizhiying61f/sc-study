package com.lzhiy.sc.common.core.util;

/**
 * Created by fp295 on 2018/5/12.
 */
public class UUID {

    public static String uuid32 () {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
