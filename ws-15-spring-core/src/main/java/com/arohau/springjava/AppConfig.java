package com.arohau.springjava;


import com.arohau.springjava.dao.UserDao;
import com.arohau.springjava.dao.UserDaoImpl;
import com.arohau.springjava.dao.UserDaoV2Impl;
import com.arohau.springjava.service.UserService;
import com.arohau.springjava.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public UserService userServiceImpl(UserDao userDaoImpl) {
        return new UserServiceImpl(userDaoImpl);
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
