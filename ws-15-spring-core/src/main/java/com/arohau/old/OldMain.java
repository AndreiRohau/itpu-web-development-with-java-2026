package com.arohau.old;

import com.arohau.old.domain.User;
import com.arohau.old.service.UserService;
import com.arohau.old.service.UserServiceImpl;

import java.util.List;

public class OldMain {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.status();

        userService.saveUser(new User("saw", "student"));
        userService.saveUser(new User("jaw", "student"));
        userService.saveUser(new User("law", "teacher"));
        System.out.println();

        List<User> users1 = userService.getUsers();
        System.out.println("All users: ");
        System.out.println(users1);
        System.out.println();

        Long id = 1L;
        User userById1 = userService.getUserById(id);
        System.out.println("User by id: " + id);
        System.out.println(userById1);
        System.out.println();

        userService.updateUserById(new User(id, "saw", "teacher"));
        User userById2 = userService.getUserById(id);
        System.out.println("User updated role by id: " + id);
        System.out.println(userById2);
        System.out.println();

        Long deleteId = 2L;
        userService.deleteUserById(deleteId);
        List<User> users2 = userService.getUsers();
        System.out.println("User deleted by id: " + deleteId);
        System.out.println(users2);
        System.out.println();


    }
}
