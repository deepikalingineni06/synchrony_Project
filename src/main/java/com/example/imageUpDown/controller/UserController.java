package com.example.imageUpDown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imageUpDown.entity.UserEntity;
import com.example.imageUpDown.exception.RecordNotFoundException;
import com.example.imageUpDown.serviceImple.UserService;





 
@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserService userService;
    
    
    @PostMapping("/save") // http://localhost:4444/user/save
	public ResponseEntity<String> addUser(@RequestBody UserEntity userEntity)
	{
    	userService.addUser(userEntity);
		return new ResponseEntity<String>("User Added",HttpStatus.OK);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userName)
	{
		UserEntity user=userService.getUser(userName);
		return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
	}
 
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> list = userService.getAll();
 
        return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        UserEntity entity = userService.getUserById(id);
 
        return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<UserEntity> createOrUpdateUser(UserEntity user)
                                                    throws RecordNotFoundException {
        UserEntity updated = userService.createOrUpdate(user);
        return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	userService.deleteById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}