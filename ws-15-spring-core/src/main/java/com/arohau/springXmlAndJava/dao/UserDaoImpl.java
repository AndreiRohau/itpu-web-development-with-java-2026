package com.arohau.springXmlAndJava.dao;

import com.arohau.springXmlAndJava.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> usersDb = new HashMap<>();
    private static final AtomicLong indx = new AtomicLong();

    @Override
    public synchronized User save(User user) {
        user.setId(indx.incrementAndGet());
        usersDb.put(user.getId(), user);
        return user;
    }

    @Override
    public synchronized List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.addAll(usersDb.values());
        return users;
    }

    @Override
    public synchronized User getById(Long id) {
        return usersDb.get(id);
    }

    @Override
    public synchronized User updateById(User user) {
        usersDb.put(user.getId(), user);
        return user;
    }

    @Override
    public synchronized boolean deleteById(Long id) {
        usersDb.remove(id);
        return !usersDb.containsKey(id);
    }
}
