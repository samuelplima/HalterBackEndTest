package com.herd.test.enuns;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum HerdEnum {

    ACTIVATED("0"),

    DEACTIVATED("1"),

    DEFECTIVE("2");

    private String value;


}
