package com.arohau.repository.impl;

import com.arohau.domain.Page;
import com.arohau.domain.User;
import com.arohau.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements Repository<Long, User> {
    public static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();

    private final Map<Long, User> FAKE_DATABASE_USER_TABLE = new HashMap<>();
    private long idSequence = 0l;

    @Override
    public synchronized Long getNextId() {
        return ++idSequence;
    }

    @Override
    public synchronized User findBy(Long id) {
        return FAKE_DATABASE_USER_TABLE.get(id);
    }

    public synchronized User findByLoginAndPassword(User user) {
        User found = null;
        for (User item : FAKE_DATABASE_USER_TABLE.values()) {
            if (item.getLogin().equalsIgnoreCase(user.getLogin())
                    && item.getPassword().equals(user.getPassword().toLowerCase())) {
                found = item;
                break;
            }
        }
        return found;
    }

    @Override
    public synchronized Page<User> getUsers(Page<User> userPage) {
        return null;
    }

    @Override
    public synchronized Long save(User user) {
        final Long id = user.getId();
        FAKE_DATABASE_USER_TABLE.put(user.getId(), user);
        return id;
    }

    @Override
    public synchronized User update(User user) {
        FAKE_DATABASE_USER_TABLE.put(user.getId(), user);
        return user;
    }

    @Override
    public synchronized void delete(Long id) {
        FAKE_DATABASE_USER_TABLE.remove(id);
    }
}
