package com.demo.web.controller;

import com.demo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class HelloWordController {

    @Autowired
    private CommonService commonService;
    @Value("${env}")
    private String env;

    @GetMapping(value = "/api/hello")
    public String hello(){
        String result1 = commonService.test("hello");
        String result2 = commonService.test("hello");
        String result3 = commonService.testFinal("hello");
        System.out.println("env:" + env);
        return "第一次调用"+result1 + ", 第二次调用" + result2;
    }

    @GetMapping(value = "/api/hello2")
    public String mockStatic(){
        String result = commonService.test("hello");
        System.out.println(result);
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        return result;
    }
}
