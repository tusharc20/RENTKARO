package com.rentkaro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excceptions.CustomException;
import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.pojos.User;
import com.rentkaro.pojos.UserAddress;
import com.rentkaro.repository.UserRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	public UserRepository userRepo;

	@Override 
	public String deleteAccount(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		userRepo.delete(user);
		return "User " + user.getFirstName() + " " + user.getLastName() + " details deleted!";
	}

	@Override
	public ProfileDTO userDetails(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		return new ProfileDTO(user.getFirstName(),user.getLastName(),user.getUserEmail(),user.getUserMobileNo(),user.getUserAddress().getAddress1(),user.getUserAddress().getAddress2(),user.getUserAddress().getCity(),user.getUserAddress().getState(),user.getUserAddress().getPincode(),user.getUserAddress().getCountry());
	}

	@Override
	public String updateDetails(Long id,ProfileDTO profile) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		user.setFirstName(profile.getFirstName());
		user.setLastName(profile.getLastName());
		user.setUserEmail(profile.getUserEmail());
		user.setUserMobileNo(profile.getUserMobileNo());
		UserAddress userAddress = user.getUserAddress();
		userAddress.setAddress1(profile.getAddress1());
		userAddress.setAddress2(profile.getAddress2());
		userAddress.setCity(profile.getCity());
		userAddress.setState(profile.getState());
		userAddress.setPincode(profile.getPincode());
		userAddress.setCountry(profile.getCountry());
		user.setUserAddress(userAddress);
		userRepo.save(user);
		return "Profile Successfully Updated";
	}

	@Override
	public List<ProductDTO> myProducts(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
