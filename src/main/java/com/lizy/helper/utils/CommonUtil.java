package com.lizy.helper.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author lizy
 * @date 2021/8/27 下午2:05
 */
public class CommonUtil {

    private static final String STRING_SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";

    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成四位盐
     *
     * @return
     */
    public static String generateSalt() {
        char[] nonceChars = new char[4];  //指定长度，自己可以设置
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = STRING_SYMBOLS.charAt(RANDOM.nextInt(STRING_SYMBOLS.length()));
        }
        return new String(nonceChars);
    }


    public static void main(String[] args) {
        System.out.println(generateSalt());
    }
}