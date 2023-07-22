package com.rentkaro.service;

import java.util.List;

import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.pojos.User;

public interface ProfileService {

//	String deleteAccount(User user);
	String deleteAccount(Long id);
	
//	ProfileDTO userDetails(User user);
	ProfileDTO userDetails(Long id);

//	String updateDetails(User user,ProfileDTO profile);
	String updateDetails(Long id,ProfileDTO profile);

//	List<ProductDTO> myProducts(User user);
	List<ProductDTO> myProducts(Long id);

}
