package com.demo.web.controller;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import com.demo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;

@RestController
public class HelloWordController {

    @Resource
    private CommonService commonService;
    @Resource
    private PhoneService phoneService;
    @Value("${env}")
    private String env;

    @GetMapping(value = "/api/hello")
    public String hello(){
        Phone phone = new Phone("红米");
        System.out.println(phone.getName());
        String result1 = commonService.test("hello");
        String result2 = commonService.test("hello");
        String result3 = commonService.test("hello");

        System.out.println("env:" + env);

        return "第一次调用"+result1 + ", 第二次调用" + result2 + ", 第三次调用" + result3;
    }

    @GetMapping(value = "/api/mockFinal")
    public String mockFinal(){
        String result = commonService.testFinal("hello");
        return result;
    }

    @GetMapping(value = "/api/callRealMethod")
    public int callRealMethod(){
        return commonService.callRealMethod();
    }

    @GetMapping(value = "/api/test")
    public String test(int num){
        int sum = 0;
        if (num > 1) {
            sum += 1;
        }
        return commonService.test("test"+sum);
    }
}
