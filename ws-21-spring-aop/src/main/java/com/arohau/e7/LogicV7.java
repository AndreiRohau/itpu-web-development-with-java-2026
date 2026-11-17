package com.arohau.e7;

import org.springframework.stereotype.Service;

@Service
public class LogicV7 {

    public String combineValues(String a, int b) {
        System.out.println("in method");
        return a + b;
    }
}
