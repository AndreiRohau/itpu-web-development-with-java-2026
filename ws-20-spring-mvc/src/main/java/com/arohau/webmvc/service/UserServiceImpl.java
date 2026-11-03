package com.arohau.webmvc.service;

import com.arohau.webmvc.entity.User;
import com.arohau.webmvc.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(com.arohau.webmvc.model.User data) {
        // check login does not exist (unique login)
        // check passwords equals
        User user = prepareUserEntity(data);
        return userRepository.save(user);
    }

    private User prepareUserEntity(com.arohau.webmvc.model.User data) {
        User user = new User();
        user.setName(data.getName());
        user.setLogin(data.getLogin());
        user.setPassword(data.getPassword1());
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByLogin(String login) {
        // check login is nonNull or nonBlanc
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public boolean updateUser(com.arohau.webmvc.model.User user) {
        return false;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }
}
