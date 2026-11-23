package com.arohau.edu.service;

import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    public String textToUppercase(String text) {
        return text.toUpperCase();
    }
    public String textToLowercase(String text) {
        return text.toLowerCase();
    }
}
