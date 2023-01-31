package com.spring.security.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class MyController {

    @GetMapping("/public")
    public ResponseEntity<String> publicResponse(){
        return new ResponseEntity<>("This is the public user", HttpStatus.OK);
    }

    @GetMapping("/normal")
    public ResponseEntity<String> normalResponse(){
        return new ResponseEntity<>("This is the normal user", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminResponse(){
        return new ResponseEntity<>("This is the admin user", HttpStatus.OK);
    }
}
