package com.example.imageUpDown.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.imageUpDown.entity.JWTRequest;
import com.example.imageUpDown.entity.JWTResponse;
import com.example.imageUpDown.entity.UserEntity;
import com.example.imageUpDown.repository.UserRepository;
import com.example.imageUpDown.utility.JWTService;




@RestController
public class JWTController {

    @Autowired
    JWTService jWTService;
   
    @Autowired
    UserRepository userRepository;

   

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<JWTResponse> getToken(@RequestBody JWTRequest jwtRequest){
        System.out.println(jwtRequest);
        
//        this.personRepository.save(new Person(1, "raju",
// 			   new BCryptPasswordEncoder().encode("Raju@123"), "USER"));
// 		this.personRepository.save(new Person(3, "prem",
// 				new BCryptPasswordEncoder().encode("Prem@123"), "ADMIN"));
// 		this.personRepository.save(new Person(2, "ravi",
//			new BCryptPasswordEncoder().encode("Ravi@123"), "MENTOR"));
        
        
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1l);
        userEntity.setUserName("DEFAULT");
        userEntity.setPassword(new BCryptPasswordEncoder().encode("DEFAULT"));
        userEntity.setFirstName("raju");
        userEntity.setLastName("sharma");
        userEntity.setEmail("default@gmail.com");
        userEntity.setRoles("ADMIN");
        userEntity.setActive(true);
        
        
        userRepository.save(userEntity);   
        
 		
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName()
                    , jwtRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(new JWTResponse(jWTService.generateToken(jwtRequest.getUserName())));
            } else {
                throw new UsernameNotFoundException("Person is not found sorry !!");
            }
        }
        catch (Exception ex){
            throw new UsernameNotFoundException("user name not found");
        }
    }
}
