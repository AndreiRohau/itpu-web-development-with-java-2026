package com.arohau.e1;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SomeService {
    public static final Logger LOGGER = Logger.getLogger(SomeService.class.getName());

    @CustomWrapper
    public UserDto login(UserDto userDto) {
        System.out.println("7 - SomeService#login() - BEGIN");
        System.out.println("    " + userDto);
        userDto.setLogin(userDto.getLogin() + "_LOGIN_MUTATION");
        userDto.setPassword(userDto.getPassword() + "_LOGIN_MUTATION");
        System.out.println("8 - SomeService#login() - END");
        return userDto;
    }

    @Log
    public int textLength(String text) {
//        System.out.println("SomeService#textLength()");
        LOGGER.warning("SomeService#textLength()");
        return text.length();
    }

}
