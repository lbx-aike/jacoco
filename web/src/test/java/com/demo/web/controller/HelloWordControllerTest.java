package com.demo.web.controller;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Calendar;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("这是一个demo")
public class HelloWordControllerTest {

    @InjectMocks
    HelloWordController helloWordController;
    @Mock
    CommonService commonService;
//    @Spy
//    CommonService commonService;

    @BeforeAll
    static void init() {
        System.out.println("---- 执行@BeforeAll ----");
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(helloWordController, "env", "test");
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
        test.set(2022, Calendar.JUNE, 1);
        try (MockedStatic<Calendar> cal = mockStatic(Calendar.class)) {
            cal.when(Calendar::getInstance).thenReturn(test);
            System.out.println(Calendar.getInstance().getTime());
        }
    }

    @Test
    @DisplayName("mock construction")
    public void mockConstructionTest () {
        System.out.println(new Phone("小米").getName());
        try (MockedConstruction<Phone> mocked = mockConstructionWithAnswer(Phone.class, invocationOnMock -> {
            if (invocationOnMock.getMethod().equals(Phone.class.getMethod("getName"))) {
                return "苹果";
            }
            return null;
        })) {
            System.out.println(new Phone("华为").getName());
        }
        System.out.println(new Phone("VIVO").getName());
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
    static void afterAll() {
        System.out.println("---- 执行@AfterAll ----");
    }


}
