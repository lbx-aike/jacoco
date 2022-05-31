package com.demo.service.impl;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import com.demo.service.PhoneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    PhoneService phoneService = new PhoneServiceImpl();
    @Spy//适用局部mock
    CommonService commonService = new CommonServiceImpl();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("执行真实方法，返回真实的值")
    public void spyTest0(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        Phone phone = phoneService.getPhoneByName("小米");
        assertTrue(phone.getCpu().contains("高通骁龙"));
    }

    @Test
    @DisplayName("执行真实方法，返回虚假的值")
    public void spyTest1(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        when(commonService.getCpu()).thenReturn("天玑100");
        Phone phone = phoneService.getPhoneByName("小米");
        assertEquals(phone.getCpu(), "天玑100");
    }

    @Test
    @DisplayName("执行虚假方法，返回虚假的值")
    public void spyTest2(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        doReturn("天玑100").when(commonService).getCpu();
        Phone phone = phoneService.getPhoneByName("小米");
        assertEquals(phone.getCpu(), "天玑100");
    }

    @Test
    @DisplayName("无返回值方法")
    public void spyTest3(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        doNothing().when(commonService).testVoid(anyString());
        phoneService.createPhone("小米");
        verify(commonService, times(1)).testVoid(anyString());
    }
}