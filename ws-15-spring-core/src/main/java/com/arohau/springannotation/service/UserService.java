package com.arohau.springannotation.service;

import com.arohau.springannotation.domain.User;

import java.util.List;

public interface UserService {
    void status();

    User saveUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    User updateUserById(User user);

    boolean deleteUserById(Long id);
}
