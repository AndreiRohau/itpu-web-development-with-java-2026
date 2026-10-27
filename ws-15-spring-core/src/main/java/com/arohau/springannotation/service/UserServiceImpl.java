package com.arohau.springannotation.service;

import com.arohau.springannotation.dao.UserDao;
import com.arohau.springannotation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

/*
 constructor dependency injection approach
 No qualifying bean of type 'com.arohau.springannotation.dao.UserDao'...
 we have 3 options to get rid of exception:
 1) comment out current constructor #1 and uncomment #2 with @Qualifier("userDaoImpl")
 1) comment out current constructor #1 and uncomment #3 with where parameter naming convention is used "userDaoV2Impl"
 3) use @Primary annotation above required Dao class
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired 
    private UserDao userDao;

    // constructor #1
    // @Autowired
    // public UserServiceImpl(UserDao userDao) {
    //     this.userDao = userDao;
    // }

    // constructor #2
//    @Autowired
//    public UserServiceImpl(@Qualifier("userDaoImpl") UserDao userDao) { // specified @Qualifier
//        this.userDao = userDao;
//    }

    // constructor #3
//    @Autowired
//    public UserServiceImpl(UserDao userDaoV2Impl) { // only param name is changed:
//        this.userDao = userDaoV2Impl;
//    }

    // setter dependency injection approach
    // @Autowired
    // public void setUserDao(UserDao userDaoImpl) {
    //     this.userDao = userDaoImpl;
    // }

    @Override
    public void status() {
        System.out.println(UserServiceImpl.class + " is injected with dao of = " + (userDao != null ? userDao.getClass() : null));
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
