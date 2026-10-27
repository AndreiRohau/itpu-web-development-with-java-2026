package com.arohau.springXmlAndJava;

import com.arohau.springXmlAndJava.dao.UserDao;
import com.arohau.springXmlAndJava.dao.UserDaoImpl;
import com.arohau.springXmlAndJava.dao.UserDaoV2Impl;
import com.arohau.springXmlAndJava.service.UserService;
import com.arohau.springXmlAndJava.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserService userServiceImpl(UserDao userDao) { // update here
        return new UserServiceImpl(userDao);
    }

    @Bean
    public UserDao userDaoImpl() {
        return new UserDaoImpl();
    }

    @Bean
    public UserDao userDaoV2Impl() {
        return new UserDaoV2Impl();
    }
}
