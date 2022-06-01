package com.demo.web.controller;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import com.demo.service.PhoneService;
import com.demo.service.impl.CommonServiceImpl;
import com.demo.service.impl.PhoneServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
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
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("这是一个demo")
public class HelloWordControllerTest {

    @InjectMocks
    HelloWordController helloWordController;
    @Mock
    CommonServiceImpl commonService = new CommonServiceImpl();

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


    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    @DisplayName("mock public")
    public void mockPublicTest() {
        //多次调用会依次返回预设好的值，超过次数，会返回最后一次预设的值
        when(commonService.test(Mockito.anyString())).thenReturn("1", "2");
        System.out.println(helloWordController.hello());
    }

    @Test
    @DisplayName("mock public final")
    public void mockPublicFinalTest() {
        when(commonService.testFinal(Mockito.anyString())).thenReturn("test");
        assertEquals(helloWordController.mockFinal(), "test");
    }

    @Test
    @DisplayName("mock static")
    public void mockStaticTest() {
        Calendar test = Calendar.getInstance();
        test.set(2022, 0, 1, 0, 0, 0);
        System.out.println(test.getTime());
        try (MockedStatic cal = mockStatic(Calendar.class)) {
            cal.when(Calendar::getInstance).thenReturn(test);
            System.out.println(Calendar.getInstance().getTime());
        }
        System.out.println(Calendar.getInstance().getTime());
    }

    @Test
    @DisplayName("mock construction")
    public void mockConstructionTest () {
        assertEquals(new Phone("小米").getName(), "小米");
        try (MockedConstruction<Phone> mocked = mockConstructionWithAnswer(Phone.class, invocationOnMock -> {
            if (invocationOnMock.getMethod().equals(Phone.class.getMethod("getName"))) {
                return "苹果";
            }
            return null;
        })) {
            assertEquals(new Phone("华为").getName(), "苹果");
        }
        assertEquals(new Phone("红米").getName(), "红米");
    }

    @Test
    @DisplayName("mock throw exception")
    public void mockThrowException() {
        try {
            when(commonService.test(anyString())).thenThrow(new RuntimeException("exception"));
            helloWordController.hello();
            fail();
        } catch (Exception e) {
            assertEquals("exception", e.getMessage());
        }
    }

    @Test
    @DisplayName("mock call real method")
    public void mockCallRealMethod() {
        when(commonService.callRealMethod()).thenCallRealMethod();
        assertTrue(helloWordController.callRealMethod() < 11);
    }

    @Test
    @DisplayName("test then answer")
    public void testThenAnswer() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenAnswer((Answer<String>) invocation -> {
            Object[] args = invocation.getArguments();
            Integer index = (Integer) args[0];
            if (index == 0) {
                return "foo";
            } else if (index == 1) {
                return "bar";
            } else {
                throw new IndexOutOfBoundsException("数组越界");
            }
        });

        assertEquals("foo", mockedList.get(0));
        assertEquals("bar", mockedList.get(1));
        try {
            mockedList.get(20);
        } catch (Exception e) {
            assertEquals("数组越界", e.getMessage());
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
