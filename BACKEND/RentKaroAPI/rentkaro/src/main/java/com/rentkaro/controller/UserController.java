package com.rentkaro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentkaro.dto.ForgotPassDTO;
import com.rentkaro.dto.UserLogInDTO;
import com.rentkaro.dto.UserSignUpDTO;
import com.rentkaro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	public UserController() {
		System.out.println("user controller bean created");
	}

//	No Need
//	@GetMapping
//	public String test() {
//		return "hi this is demo test to check controller ";
//	}

	@PostMapping("/signin")
	public String addUser(@RequestBody UserSignUpDTO user) {

		return userService.addUser(user);
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return "Logout successfull";
	}
	
	@PostMapping
	public String authenticate(@RequestBody UserLogInDTO user, HttpServletRequest request) throws JsonProcessingException {
		
		request.getSession().setAttribute("id", userService.authenticate(user).getId());
		return "Login successfull";
	}
	
	@PutMapping("/changepassword") // in future forgot pass
	public String changePassword(@RequestBody ForgotPassDTO user) {

		return userService.changePassword(user);
	}

//	No Need
//	  @GetMapping("/all")
//	  public List<UserSignUpDTO> getUsers() 
//	  { 
//		  return userService.getUsers(); 
//	  }

}
