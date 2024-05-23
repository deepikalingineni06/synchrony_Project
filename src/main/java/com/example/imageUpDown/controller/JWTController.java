package com.example.imageUpDown.controller;

import com.example.imageUpDown.utility.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token")
    public String generateToken(@RequestParam String username, @RequestParam String password) {
        return tokenService.createToken(username, password);
    }
}
