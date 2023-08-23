package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.service.HomePageService;

@RestController
@RequestMapping
public class HomePageController {

	@Autowired
	private HomePageService homeService;
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllPRoductDetails(){
		
		return ResponseEntity.status(HttpStatus.OK).body(homeService.getAllProductDetails());
				
	}
}
