package com.arohau.springjava.service;

import com.arohau.springjava.dao.UserDao;
import com.arohau.springjava.domain.User;

import java.util.List;

import static java.util.Objects.isNull;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // constructor dependency injection approach
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // setter dependency injection approach
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

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
