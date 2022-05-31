package com.demo.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestUtils3Test {

    @Test
    void testCountCharNumInStr1() {
        int result = TestUtils3.countCharNumInStr("", 'a');
        Assertions.assertEquals(0, result);
    }

    @Test
    void testCountCharNumInStr2() {
        int result = TestUtils3.countCharNumInStr("aa", 'a');
        Assertions.assertEquals(2, result);
    }
}
