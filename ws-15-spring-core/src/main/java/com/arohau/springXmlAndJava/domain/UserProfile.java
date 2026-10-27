package com.arohau.springXmlAndJava.domain;

import lombok.Data;

@Data
public class UserProfile implements Cloneable {
    private String name;
    private String surname;

    @Override
    public UserProfile clone() {
        UserProfile userProfile;
        try {
            userProfile = (UserProfile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
        return userProfile;
    }
}
