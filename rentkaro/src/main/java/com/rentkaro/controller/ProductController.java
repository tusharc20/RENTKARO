package com.rentkaro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentkaro.dto.ApiResponse;
import com.rentkaro.dto.ProductAddDTO;
import com.rentkaro.service.ProductService;



@RestController
@RequestMapping
public class ProductController {
	
	@Autowired
	private ProductService productServ;
	

	
	
	
	@PostMapping("/products/add/{userId}")
	public ResponseEntity<?> addProduct(@RequestBody ProductAddDTO productDTO,@PathVariable Long userId ){
		
		try {
			
			
			
			productServ.addProduct(productDTO,userId);
			
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to add product"));
		}
		
		
	
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product added successfully"));
	}
	
	

}
