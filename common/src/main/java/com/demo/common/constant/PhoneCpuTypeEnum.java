package com.demo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneCpuTypeEnum {

    SNAPDRAGON("高通骁龙"),
    MEDIA_TEK("天玑"),
    ;

    private String cpuType;
}
