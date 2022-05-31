package com.demo.service;

import com.demo.common.domain.Phone;

public interface PhoneService {

    Phone getPhoneByName(String name);

    Phone createPhone(String name);

}
