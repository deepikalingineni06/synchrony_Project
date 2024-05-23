package com.example.imageUpDown.controller;

import com.example.imageUpDown.entity.UserEntity;
import com.example.imageUpDown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public UserEntity getUser(@PathVariable String username) {
        return userService.fetchUserByUsername(username);
    }
}
