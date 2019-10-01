package com.test.jdbc.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserEntity {

    private String login;
    private String firstName;
    private String secondName;
    private String middleName;
    private int age;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
