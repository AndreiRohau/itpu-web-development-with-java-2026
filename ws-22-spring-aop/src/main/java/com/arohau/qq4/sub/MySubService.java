package com.arohau.qq4.sub;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MySubService {
    public void get() {
    }

    public Object getField(String fieldName) {
        return "fieldNameStub";
    }

    public List<Object> getList() {
        return new ArrayList<>();
    }
}
