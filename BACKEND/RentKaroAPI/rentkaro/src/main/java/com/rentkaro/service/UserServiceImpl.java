package com.rentkaro.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excceptions.CustomException;
import com.rentkaro.dto.ForgotPassDTO;
import com.rentkaro.dto.UserLogInDTO;
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
	public String addUser(UserSignUpDTO obj) {
		User userObj = modelMap.map(obj, User.class);
		UserAddress userAddress = modelMap.map(obj, UserAddress.class);
		userObj.addUserAddress(userAddress);
		userRepo.save(userObj);
		return "User " + userObj.getFirstName() + " " + userObj.getLastName() + " Added Successfully!";

	}
//	@Override
//	public List<UserSignUpDTO> getUsers() {
//		List<UserSignUpDTO> usersDTO = new ArrayList<>();
//		List<User> users =  userRepo.findAll();
//		for(User temp : users) {
//			usersDTO.add(new UserSignUpDTO(temp.getFirstName(),temp.getLastName(),temp.getUserEmail(),temp.getUserPassword(),temp.getUserMobileNo(),temp.getUserAddress().getAddress1(),temp.getUserAddress().getAddress2(),temp.getUserAddress().getCity(),temp.getUserAddress().getState(),temp.getUserAddress().getPincode(),temp.getUserAddress().getCountry()));
//		}
//		return usersDTO;
//	}

	@Override
	public User authenticate(UserLogInDTO user) {
		User userObj = userRepo.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword())
						.orElseThrow(() -> new CustomException("Bad Credentials , Login Failed!"));
				return userObj;
	}

	@Override
	public String changePassword(ForgotPassDTO user) {
		// TODO Auto-generated method stub
		User userObj = userRepo.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword())
				.orElseThrow(() -> new CustomException("Bad Credentials , Email Password does not match!"));
		userObj.setUserPassword(user.getNewPassword());
		userRepo.save(userObj);
		return "Passwrod Updated Successfully!";
	}
}
