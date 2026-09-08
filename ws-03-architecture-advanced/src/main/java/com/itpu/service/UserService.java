package com.itpu.service;

import com.itpu.domain.User;

public interface UserService {
    Long saveUser(User user);
    User findUser(Long id);
}
