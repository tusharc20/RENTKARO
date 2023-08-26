package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.service.ProductSortingService;

@RestController
@RequestMapping
public class ProductSortingController {
	
	@Autowired
	private ProductSortingService prodService;
	
	@GetMapping("/products/sorting/{criteria}")
	public ResponseEntity<?>getProductsInSortedManner(@PathVariable String criteria){
		
		return ResponseEntity.status(HttpStatus.OK).body(prodService.getAllProductsInSortedManner(criteria));
	}
	

}
