package com.demo.service.impl;

import com.demo.service.CommonService;
import com.demo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    @Lazy
    private PhoneService phoneService;

    @Override
    public String test(String param) {
        return "commonService test method, param:"+param;
    }

    @Override
    public final String testFinal(String param) {
        return "commonService testFinal method, param:"+param;
    }

    @Override
    public String getCpu() {
        Random r = new Random();
        System.out.println("========真实方法被执行========");
        return "高通骁龙"+r.nextInt(10);
    }

    @Override
    public void testVoid(String name) {
        System.out.println("啥也没干");
    }

    @Override
    public int callRealMethod() {
        Random r = new Random();
        System.out.println("========真实方法被执行========");
        return r.nextInt(10);
    }
}
