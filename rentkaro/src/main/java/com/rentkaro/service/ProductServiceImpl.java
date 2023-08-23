package com.rentkaro.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rentkaro.dto.ApiResponse;
import com.rentkaro.dto.ProductAddDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.User;
import com.rentkaro.repository.UserRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	
	@Autowired 
	private ModelMapper mapper;
	
	@Autowired 
	private ProductAddRepository productRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public ResponseEntity<?> addProduct(ProductAddDTO productDTO,Long userId) {
		
		User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("Invalid User"));
		
		
	       productDTO.setIsAvailable(true);
	       
	       
	       
	       Product product=mapper.map(productDTO, Product.class);
	       
	      user.addProduct(product);
	      
	    //  productRepo.save(product);
	      
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Product added Successfully"));
	}

	

}
