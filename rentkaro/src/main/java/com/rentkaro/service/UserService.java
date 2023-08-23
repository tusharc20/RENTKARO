package com.rentkaro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.rentkaro.dto.UserSignUpDTO;
import com.rentkaro.pojos.User;

@Service
@org.springframework.transaction.annotation.Transactional
public interface UserService {
	
	User addUser(UserSignUpDTO obj);

	List<User> getUsers();
	
}
