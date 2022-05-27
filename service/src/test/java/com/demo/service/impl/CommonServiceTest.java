package com.demo.service.impl;

import com.demo.service.CommonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommonServiceTest {

    @InjectMocks
    CommonService commonService = new CommonServiceImpl();

    @BeforeAll
    static void init() {

    }



}