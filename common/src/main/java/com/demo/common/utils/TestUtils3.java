package com.demo.common.utils;


public class TestUtils3 {

    public static int countCharNumInStr(String testStr, char countChar) {
        char[] testStrChars = testStr.toCharArray();
        return countCharNum(countChar, testStrChars);
    }

    private static int countCharNum(char countChar, char[] testStrChars) {
        int count = 0;
        for (char strChar : testStrChars) {
            if (strChar == countChar) {
                count++;
            }
        }
        return count;
    }
}
