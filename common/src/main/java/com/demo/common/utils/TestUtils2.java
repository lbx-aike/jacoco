package com.demo.common.utils;


public class TestUtils2 {

    public static int countCharNumInStr(String testStr, char countChar) {
        char[] testStrChars = testStr.toCharArray();
        int count = 0;
        for (char strChar : testStrChars) {
            if (strChar == countChar) {
                count++;
            }
        }
        return count;
    }
}
