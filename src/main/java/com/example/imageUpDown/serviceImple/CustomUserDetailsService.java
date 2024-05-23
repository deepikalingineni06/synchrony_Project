package com.example.imageUpDown.serviceImple;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.imageUpDown.entity.CustomUser;
import com.example.imageUpDown.repository.UserRepository;



@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new CustomUser(userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Person is not found with this personName "+userName)));
    }
}
