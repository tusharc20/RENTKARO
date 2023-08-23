package com.rentkaro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.User;
import com.rentkaro.repository.ProductRepository;
import com.rentkaro.repository.UserRepository;

@Service
@Transactional
public class ProductReturnServiceImpl implements ProductReturnService {

	@Autowired 
	private ProductRepository productRepo;
	
	@Autowired 
	private UserRepository userRepo;
	
	public String returnRentedProduct(Long renterId,Long productId){
		
		Product product=productRepo.findById(productId).orElseThrow(()->new RuntimeException("Invalid Product Id"));
		
		User user=userRepo.findById(renterId).orElseThrow(()->new RuntimeException("Invalid user id"));
		
		product.setIsAvailable(true);
		product.setRenter(null);
		
		user.setRentalDate(null);
		user.setReturnDate(null);
		user.setRentedProduct(null);
		
		return "Product returned Successfullly.";
		
	}
	
	
}
