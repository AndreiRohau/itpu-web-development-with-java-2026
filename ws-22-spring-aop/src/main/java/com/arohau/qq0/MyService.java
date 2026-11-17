package com.arohau.qq0;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    @MyAnnotation
    public String getString(String arg) {
        return "some text";
    }
}
