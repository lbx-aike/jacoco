package com.demo.web.controller;

import com.demo.service.CommonService;
import com.demo.web.StartApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StartApplication.class)
public class SpringBootStartTest {

    @Autowired
    private HelloWordController helloWordController;

    @MockBean
    CommonService commonService;

    @Test
    public void test1() {
        when(commonService.test(anyString())).thenReturn("test");
        System.out.println(helloWordController.test(1));
    }

}
