package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.service.ProductOrCategoryService;

@RestController
@RequestMapping
public class ProductOrCategoryController {
	
	@Autowired
	private ProductOrCategoryService productService;
	
	@GetMapping("/products/search/{productDescription}")
	public ResponseEntity<?>getProductsByDescription(@PathVariable String productDescription ){
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(productDescription));
	}

}
