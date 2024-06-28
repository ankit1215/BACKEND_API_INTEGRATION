package com.crypto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.entity.User;
import com.crypto.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("signup")
	public User signUp(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/login")
	public Optional<User> login(@RequestBody User user){
		return userService.findbyUsername(user.getUsername());
	}
	
	@PutMapping("/update")
	public User updateUser( @RequestBody User user) {
		return userService.updateUser(user);
	}

}
