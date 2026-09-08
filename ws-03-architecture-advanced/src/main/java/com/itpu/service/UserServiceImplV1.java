package com.itpu.service;

import com.itpu.dao.UserDao;
import com.itpu.domain.User;

public class UserServiceImplV1 implements UserService {

    private UserDao userDao;

    @Override
    public Long saveUser(User user) {
        // validate user
        // set up default data...
        return userDao.saveUser(user);
    }

    @Override
    public User findUser(Long id) {
        return null;
    }
}
