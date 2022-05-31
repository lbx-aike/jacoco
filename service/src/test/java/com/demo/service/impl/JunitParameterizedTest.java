package com.demo.service.impl;

import com.demo.aggregator.CsvToPhone;
import com.demo.aggregator.PhoneAggregator;
import com.demo.common.constant.PhoneCpuTypeEnum;
import com.demo.common.domain.Phone;
import com.demo.converter.EnumToString;
import com.demo.converter.ToStringArgumentConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.*;

class JunitParameterizedTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2"})
    void testTest(int i) {
        System.out.println(i);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2022-05-01"})
    void testTest(@JavaTimeConversionPattern("yyyy-MM-dd") LocalDate date) {
        System.out.println(date);
    }

    @ParameterizedTest
    @EnumSource(PhoneCpuTypeEnum.class)
    void testTest(@ConvertWith(ToStringArgumentConverter.class) String cpuType) {
        System.out.println(cpuType);
    }

    @ParameterizedTest
    @EnumSource(PhoneCpuTypeEnum.class)
    void testTest11(@EnumToString String cpuType) {
        System.out.println(cpuType);
    }

    @ParameterizedTest
    @EnumSource(PhoneCpuTypeEnum.class)
    void testTest(PhoneCpuTypeEnum cpuTypeEnum) {
        System.out.println(cpuTypeEnum.getCpuType());
    }

    @ParameterizedTest(name = "第{index}次测试, 第一个参数:{0}, 第二个参数:{1}")
    @CsvSource({"小米, 8", "华为, 7"})
    void testTest(String name, int cpu) {
        Phone phone = new Phone(name);
        phone.setCpu(String.valueOf(cpu));
        System.out.println(phone);
    }

    @ParameterizedTest
    @CsvSource({"红米, 8", "VIVO, 7"})
    void testTest(ArgumentsAccessor argumentsAccessor) {
        Phone phone = new Phone(argumentsAccessor.getString(0));
        phone.setCpu(argumentsAccessor.getString(1));
        System.out.println(phone);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/test.csv", numLinesToSkip = 1)
    void testTest1(ArgumentsAccessor argumentsAccessor) {
        Phone phone = new Phone(argumentsAccessor.getString(0));
        phone.setCpu(argumentsAccessor.getString(1));
        System.out.println(phone);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/test.csv", numLinesToSkip = 1)
    void testTest1(@AggregateWith(PhoneAggregator.class) Phone phone) {
        System.out.println(phone);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/test.csv", numLinesToSkip = 1)
    void testTest11(@CsvToPhone Phone phone) {
        System.out.println(phone);
    }

    @ParameterizedTest
    @MethodSource("phoneProvider")
    void testTest2(Phone phone) {
        System.out.println(phone);
    }

    static Stream<Phone> phoneProvider() {
        Phone phone1 = new Phone("苹果");
        phone1.setCpu("16");
        Phone phone2 = new Phone("红魔");
        phone2.setCpu("8");
        return Stream.of(phone1, phone2);
    }

    @ParameterizedTest
    @MethodSource
    void argumentsTest(Phone phone, String result) {
        assertEquals(phone.getName(), result);
    }

    static Stream<Arguments> argumentsTest() {
        Phone phone1 = new Phone("苹果");
        phone1.setCpu("16");
        Phone phone2 = new Phone("红魔");
        phone2.setCpu("8");
        return Stream.of(arguments(phone1, phone1.getName()),
                arguments(phone2, phone2.getName()));
    }

    @ParameterizedTest
    @MethodSource
    void argumentsTest1(ArgumentsAccessor argumentsAccessor) {
        Phone phone = argumentsAccessor.get(0, Phone.class);
        assertEquals(phone.getName(), argumentsAccessor.getString(1));
    }

    static Stream<Arguments> argumentsTest1() {
        Phone phone1 = new Phone("苹果");
        phone1.setCpu("16");
        Phone phone2 = new Phone("红魔");
        phone2.setCpu("8");
        return Stream.of(arguments(phone1, phone1.getName()),
                arguments(phone2, phone2.getName()));
    }

}
