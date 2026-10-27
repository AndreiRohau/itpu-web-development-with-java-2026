package com.arohau.springXmlAndJava;

import org.springframework.stereotype.Component;

@Component
public class SomeBeanComponent {
    public void status() {
        System.out.println("SomeBeanComponent # status()");
    }
}
