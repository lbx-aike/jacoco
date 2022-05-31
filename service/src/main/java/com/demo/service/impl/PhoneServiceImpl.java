package com.demo.service.impl;

import com.demo.common.domain.Phone;
import com.demo.service.CommonService;
import com.demo.service.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Resource
    private CommonService commonService;

    @Override
    public Phone getPhoneByName(String name) {
        Phone phone = new Phone(name);
        phone.setCpu(commonService.getCpu());
        return phone;
    }

    @Override
    public Phone createPhone(String name) {
        Phone phone = new Phone(name);
        commonService.testVoid(name);
        return phone;
    }
}
