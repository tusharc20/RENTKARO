package com.rentkaro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.MyProductDTO;
import com.rentkaro.dto.SellProductDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {
	@Autowired
	public ProductService productService;

	public ProductController() {
		System.out.println("product controller bean created");
	}

	@PostMapping("/sell")
	public Long addProduct(@RequestBody SellProductDTO product, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");

		return productService.addProduct(id,product);
	}
	
	@GetMapping("/{prodId}")
	public MyProductDTO displayProduct(@PathVariable Long prodId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		MyProductDTO prod = productService.displayProduct(prodId);
		if(id.equals(prod.getOwnerId())) {
			return prod;
		}
		else {
			prod.setOwnerId(null);
			return prod;
		}
	}
	
	@GetMapping("/myproduct/{prodId}")
	public MyProductDTO displayMyProduct(@PathVariable Integer prodId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		MyProductDTO prod = productService.displayMyProduct(id,prodId);
		return prod;
	}
	
	@GetMapping("/rent/{prodId}")
	public String rentProduct(@PathVariable Long prodId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		return productService.rentProduct(id,prodId);
	}
}
