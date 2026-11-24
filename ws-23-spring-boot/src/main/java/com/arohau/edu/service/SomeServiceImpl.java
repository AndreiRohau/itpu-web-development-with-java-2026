package com.arohau.edu.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary 
public class SomeServiceImpl implements SomeService {
    public String textToUppercase(String text) {
        return text.toUpperCase();
    }
    public String textToLowercase(String text) {
        return text.toLowerCase();
    }
}
