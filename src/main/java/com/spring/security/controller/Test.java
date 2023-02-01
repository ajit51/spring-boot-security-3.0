package com.spring.security.controller;

import com.spring.security.config.MyConfigSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {

    @Autowired
    private static PasswordEncoder passwordEncoder;
    public static void main(String[] args) {

    }
}
