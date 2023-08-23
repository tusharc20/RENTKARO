package com.rentkaro.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.ApiResponse;
import com.rentkaro.dto.UserSignUpDTO;
import com.rentkaro.pojos.User;
import com.rentkaro.pojos.UserAddress;
import com.rentkaro.repository.UserRepository;



@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepo;
	@Autowired
	public ModelMapper modelMap;
	@Override
	public ResponseEntity<?> addUser(UserSignUpDTO obj) {
		
		User user1 = userRepo.findByUserEmail(obj.getUserEmail());
		User user2 = userRepo.findByUserMobileNo(obj.getUserMobileNo());
		
		if(user1!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Account with this Email already exists."));
		}
		
		if(user2!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Account with this Mobile Number already exists."));
		}
		
		User userObj = modelMap.map(obj, User.class);
		UserAddress userAddress = modelMap.map(obj, UserAddress.class);
		userObj.addUserAddress(userAddress);
		if(userRepo.save(userObj)!=null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("User created successfully"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("User creation failed"));
		
	}
	@Override
	public List<User> getUsers() {
	
		return userRepo.findAll();
	}
	@Override
	public ResponseEntity<?> validateUser(String email, String password) {
		
		User user = userRepo.findByUserEmail(email);
		
		if(user==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Please enter valid email address"));
		}
		else {
			if((user.getUserPassword()).equals(password)) {
				
				UserSignUpDTO userDTO=modelMap.map(user, UserSignUpDTO.class);
				
				userDTO.setAddress1(user.getUserAddress().getAddress1());
				userDTO.setAddress2(user.getUserAddress().getAddress2());
				userDTO.setCity(user.getUserAddress().getCity());
				userDTO.setPincode(user.getUserAddress().getPincode());
				userDTO.setState(user.getUserAddress().getState());
				userDTO.setCountry(user.getUserAddress().getCountry());
				
				
				
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Please enter correct password"));
				
			}
		}
		
		
	}
}
