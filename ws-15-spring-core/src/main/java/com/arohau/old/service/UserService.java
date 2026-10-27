package com.arohau.old.service;

import com.arohau.old.domain.User;

import java.util.List;

public interface UserService {
    void status();

    User saveUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    User updateUserById(User user);

    boolean deleteUserById(Long id);
}
