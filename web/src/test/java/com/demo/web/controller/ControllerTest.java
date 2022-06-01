package com.demo.web.controller;

import com.demo.service.CommonService;
import com.demo.web.StartApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloWordController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CommonService commonService;

    @Test
    public void test() throws Exception {
        when(commonService.test(anyString())).thenReturn("test");
        this.mvc.perform(get("/api/test/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
