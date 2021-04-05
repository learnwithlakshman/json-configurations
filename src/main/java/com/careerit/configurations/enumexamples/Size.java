package com.careerit.configurations.enumexamples;

import lombok.Getter;

@Getter
public enum Size {
    S(36),M(38),L(40),XL(42),XXL(44);

    private int length;

    private Size(int length){
        this.length = length;
    }

}
