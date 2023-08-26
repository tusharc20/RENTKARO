package com.rentkaro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.ProductDTO;
import com.rentkaro.service.ProductService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class HomeController {
	@Autowired
	public ProductService productService;

	public HomeController() {
		System.out.println("Home controller bean created");
	}
	
	@GetMapping
	public List<ProductDTO> displayProducts(HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		return productService.displayProducts(id);
	}
	
	@GetMapping("/{category}")
	public List<ProductDTO> displayProductsByCategory(@PathVariable String category,HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		return productService.displayProductsByCategory(id,category);
	}
	
	
	
}
