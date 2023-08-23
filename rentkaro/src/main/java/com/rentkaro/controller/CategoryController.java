package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.pojos.Category;
import com.rentkaro.service.CategoryService;

@RestController
@RequestMapping
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	@GetMapping("/products/{category}")
	public ResponseEntity<?>getAllProductsByCategory(@PathVariable String category){
		
		Category productCategory=Category.valueOf(category.toUpperCase());
		
		return ResponseEntity.status(HttpStatus.OK).body(catService.getProductsByCategory(productCategory));
	}
	
}
