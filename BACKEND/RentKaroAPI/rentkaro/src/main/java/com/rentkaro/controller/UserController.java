package com.rentkaro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.UserSignUpDTO;
import com.rentkaro.pojos.User;
import com.rentkaro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	public UserController() {
		System.out.println("controller bean created");
	}

	@GetMapping
	public String test() {
		return "hi this is demo test to check controller ";
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody @Valid UserSignUpDTO user) {

		return userService.addUser(user);
	}
	
	@GetMapping("/authenticate/{email}/{password}")
	public ResponseEntity<?> authenticateUser(@PathVariable String email, @PathVariable String password){
		return userService.validateUser(email,password);
	}

	
	  @GetMapping("/all")
	  public List<User> getUsers() 
	  { 
		  return userService.getUsers(); 
	  }
	 

}
