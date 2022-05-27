package com.demo.web.controller;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.sun.javaws.exceptions.ExitException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("这是一个demo")
public class HelloWordControllerTest {

    @InjectMocks
    HelloWordController helloWordController;
    @Mock
    CommonService commonService;
//    @Spy
//    CommonService commonService;

    @BeforeAll
    public void init() {
        System.out.println("---- 执行@BeforeAll ----");
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(helloWordController, "env", "test");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("---- 执行@BeforeEach ----");
    }

    @ParameterizedTest
    @DisplayName("mock public")
    @ValueSource(strings = {"1","2"})
    public void mockPublicTest(int paramInt) {
        when(commonService.test(Mockito.anyString())).thenReturn(String.valueOf(paramInt), "test");
        lenient().when(commonService.testFinal(Mockito.anyString())).thenReturn("test");
        System.out.println(helloWordController.hello());
    }

    @Test
    @DisplayName("mock public final")
    public void mockPublicFinalTest() {
        lenient().when(commonService.test(Mockito.anyString())).thenReturn("test");
        when(commonService.testFinal(Mockito.anyString())).thenReturn("test");
        System.out.println(helloWordController.hello());
    }

    @Test
//    @EnabledOnOs({OS.LINUX, OS.MAC})
    @DisplayName("mock static")
    public void mockStaticTest() {
        Calendar test = Calendar.getInstance();
        test.set(2022, 5, 1);
        try (MockedStatic cal = mockStatic(Calendar.class)) {
            cal.when(Calendar::getInstance).thenReturn(test);
            System.out.println(Calendar.getInstance().getTime());
        }
    }

    @Test
    @DisplayName("mock construction")
    public void mockConstructionTest () {
        System.out.println(new Phone("小米").getName());
        try (MockedConstruction mocked = mockConstruction(Phone.class)) {
            Phone phone = new Phone("红米");
            when(phone.getName()).thenReturn("VIVO");
            System.out.println(phone.getName());
            Phone phone1 = new Phone("华为");
            System.out.println(phone1.getName());
            when(phone1.getName()).thenReturn("OPPO");
            System.out.println(phone1.getName());
        }
    }

    @Test
    @DisplayName("mock throw exception")
    public void mockThrowException() {
        try {
            when(commonService.test(Mockito.anyString())).thenThrow(new RuntimeException("exception"));
            helloWordController.hello();
            fail();
        } catch (Exception e) {
            assertEquals("exception", e.getMessage());
        }
    }


    @AfterEach
    public void afterEach() {
        System.out.println("---- 执行@AfterEach ----");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("---- 执行@AfterAll ----");
    }


}
