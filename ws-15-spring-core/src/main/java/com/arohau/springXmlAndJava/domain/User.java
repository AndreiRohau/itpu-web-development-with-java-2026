package com.arohau.springXmlAndJava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Cloneable {
    private Long id;
    private String login;
    private String role;
    private UserProfile userProfile = new UserProfile();

    public User(String login, String role) {
        this.login = login;
        this.role = role;
    }

    public User(Long id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    @Override
    public User clone() {
        User user;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
        user.userProfile = this.userProfile.clone();
        return user;
    }
}
