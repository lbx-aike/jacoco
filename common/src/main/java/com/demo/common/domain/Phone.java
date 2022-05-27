package com.demo.common.domain;

import lombok.Data;

@Data
public class Phone {

    private String name;

    public Phone(String name) {
        this.name = name;
    }
}
