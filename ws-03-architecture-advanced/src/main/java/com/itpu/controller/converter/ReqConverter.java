package com.itpu.controller.converter;

import com.itpu.domain.User;

public class ReqConverter {
    // extract to converter class
    public User convert(Object req) {
        // converting req to user
        return new User(req);
    }
}
