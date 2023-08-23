package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.service.ProductReturnService;

@RestController
@RequestMapping
public class ProductReturnController {
	
	@Autowired 
	private ProductReturnService productServ;
	
	@GetMapping("/products/return/{productId}/{renterId}")
	public ResponseEntity<?> returnRentedProduct(@PathVariable Long productId,@PathVariable Long renterId){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(productServ.returnRentedProduct(renterId, productId));
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	

}
