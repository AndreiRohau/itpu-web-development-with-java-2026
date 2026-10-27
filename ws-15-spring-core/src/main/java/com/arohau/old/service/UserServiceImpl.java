package com.arohau.old.service;

import com.arohau.old.dao.UserDao;
import com.arohau.old.dao.UserDaoV2Impl;
import com.arohau.old.domain.User;

import java.util.List;

import static java.util.Objects.isNull;

public class UserServiceImpl implements UserService {

//    private UserDao userDao = new UserDaoImpl();
    private UserDao userDao = new UserDaoV2Impl();

    @Override
    public void status() {
        System.out.println(UserServiceImpl.class + " is injected with dao of = " + userDao.getClass());
    }

    @Override
    public User saveUser(User user) {
        validate(user);
        User savedUser = userDao.save(user);
        return savedUser;
    }

    private void validate(User user) {
        if (isNull(user)
                || isNull(user.getLogin())
                || isNull(user.getRole())
        ) {
            throw new RuntimeException("#saveUser() -> #validation()");
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User updateUserById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userDao.deleteById(id);
    }
}
