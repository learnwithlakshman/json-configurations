package com.careerit.configurations.enumexamples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Shirt {

        private String id;
        private Size size;
        private double price;

}
