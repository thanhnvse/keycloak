package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean enabled;
    private String phoneNumber;
    private String emailVerified;
    private String phoneNumberVerified;
}
