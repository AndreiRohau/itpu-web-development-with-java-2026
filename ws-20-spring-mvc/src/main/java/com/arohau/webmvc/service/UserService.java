package com.arohau.webmvc.service;

import com.arohau.webmvc.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(com.arohau.webmvc.model.User user);
    User getUserById(Long id);
    User getUserByLogin(String login);
    List<User> getUsers();
    boolean updateUser(com.arohau.webmvc.model.User user);
    boolean deleteUserById(Long id);
}
