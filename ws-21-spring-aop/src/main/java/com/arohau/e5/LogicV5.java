package com.arohau.e5;

import org.springframework.stereotype.Service;

@Service
public class LogicV5 {

    public int textLength(String text) {
        System.out.println("Logic#textLength()");
        return text.length();
    }

    public void perform() {
        System.out.println("Logic#perform()");
    }

    public String returnText(String text) {
        return text.toUpperCase();
    }

    public String returnConstantText() {
        return "CONSTANT_TEXT";
    }
}
