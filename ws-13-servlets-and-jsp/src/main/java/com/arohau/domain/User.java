package com.arohau.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
