package com.arohau.springXmlAndJava.dao;

import com.arohau.springXmlAndJava.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class UserDaoV2Impl implements UserDao {

    private static final List<User> usersDb = new ArrayList<>();
    private static final AtomicLong indx = new AtomicLong();

    @Override
    public synchronized User save(User user) {
        user.setId(indx.incrementAndGet());
        usersDb.add(user);
        return user;
    }

    @Override
    public synchronized List<User> getAll() {
        List<User> users = usersDb
                .stream()
                .map(User::clone)
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public synchronized User getById(Long id) {
        User user = usersDb.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        return user;
    }

    @Override
    public synchronized User updateById(User user) {
        User found = usersDb.stream()
                .filter(e -> e.getId().equals(user.getId()))
                .findAny()
                .orElse(null);

        if (nonNull(found)) {
            found.setLogin(user.getLogin());
            found.setRole(user.getRole());
            found.setUserProfile(user.getUserProfile().clone());

            User foundCheck = usersDb.stream()
                    .filter(e -> e.getId().equals(found.getId()))
                    .findAny()
                    .orElse(null);
            return foundCheck;

        } else {
            throw new RuntimeException("Cannot find user to update by id");
        }
    }

    @Override
    public synchronized boolean deleteById(Long id) {
        boolean isRemoved = false;
        User found = usersDb.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);

        if (nonNull(found)) {
            isRemoved = usersDb.remove(found);
        }
        return isRemoved;
    }
}
