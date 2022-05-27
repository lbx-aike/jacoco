package com.demo.service.impl;

import com.demo.common.utils.DateUtils;
import com.demo.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String test(String param) {
        return "commonService test method, param:"+param;
    }

    @Override
    public String mockStaticCase() {
        Date now = new Date();
        return DateUtils.dateFormat(now);
    }

    @Override
    public final String testFinal(String param) {
        return "commonService test method, param:"+param;
    }
}
