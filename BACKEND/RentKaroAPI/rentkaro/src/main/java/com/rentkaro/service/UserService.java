package com.rentkaro.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentkaro.dto.UserSignUpDTO;
import com.rentkaro.pojos.User;



@Service
@org.springframework.transaction.annotation.Transactional
public interface UserService {
	
	ResponseEntity<?> addUser(UserSignUpDTO obj);

	List<User> getUsers();

	ResponseEntity<?> validateUser(String email, String password);
	
}
