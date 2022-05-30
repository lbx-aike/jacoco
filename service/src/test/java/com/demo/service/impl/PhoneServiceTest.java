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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    PhoneService phoneService = new PhoneServiceImpl();
    @Spy
    CommonService commonService = new CommonServiceImpl();



    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("spy test1")
    public void spyTest1(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        when(commonService.getCpu()).thenReturn("天玑100");
        Phone phone = phoneService.getPhoneByName("小米");
        assertEquals(phone.getCpu(), "天玑100");
    }
    @Test
    @DisplayName("spy test2")
    public void spyTest2(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        doReturn("天玑100").when(commonService).getCpu();
        Phone phone = phoneService.getPhoneByName("小米");
        assertEquals(phone.getCpu(), "天玑100");
    }

}