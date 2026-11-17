package com.arohau.e3;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LogicV3 {
    public static final Logger LOGGER = Logger.getLogger(LogicV3.class.getName());

    public int textLength(String text) {
//        System.out.println("Logic#textLength()");
        LOGGER.info("Logic#textLength()");
        return text.length();
    }

    public void perform() {
//        System.out.println("Logic#perform()");
        LOGGER.info("Logic#perform()");
    }
}
