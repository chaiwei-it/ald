package com.mood.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-01-14 9:50
 */
public class IdGen {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static Long randomLong() {
        return Math.abs(random.nextLong());
    }

//    public static void main(String[] args) {
//        System.out.println(uuid());
//        System.out.println(randomLong());
//    }
}
