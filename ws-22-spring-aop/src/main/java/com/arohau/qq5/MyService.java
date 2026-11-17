package com.arohau.qq5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {
    public void get() {
    }

    public Object getField(String fieldName) {
        return "fieldNameStub";
    }

    public List<Object> getList() {
        return new ArrayList<>();
    }
}
