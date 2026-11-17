package com.arohau.e1;

import org.springframework.stereotype.Service;

@Service
public class LogicV1 {

    @Log
    public int count(int num) {
        return num++;
    }

    @Log
    public int textLength(String text) {
        return text.length();
    }

    @NewAnnotation
    public void newMethod() {
        System.out.println("new method called");
    }

}
