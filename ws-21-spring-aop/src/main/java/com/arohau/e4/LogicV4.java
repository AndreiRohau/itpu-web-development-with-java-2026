package com.arohau.e4;

import com.arohau.e3.LogicV3;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LogicV4 {
    public static final Logger LOGGER = Logger.getLogger(LogicV3.class.getName());

    public int textLength(String text) {
//        System.out.println("Logic#textLength(text)");
        LOGGER.info("Logic#textLength(text)");
        return text.length();
    }

    public int textLength(String text, int i) {
//        System.out.println("Logic#textLength(text, i)");
        LOGGER.info("Logic#textLength(text, i)");
        return text.length();
    }

    public void perform() {
//        System.out.println("Logic#perform()");
        LOGGER.info("Logic#perform()");
    }
}
