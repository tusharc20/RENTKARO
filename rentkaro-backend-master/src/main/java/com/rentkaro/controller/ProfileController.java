package com.rentkaro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.MyProductDTO;
import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.dto.RatingDTO;
import com.rentkaro.service.ProductService;
import com.rentkaro.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProfileController {

	@Autowired
	public ProfileService profileService;
	@Autowired
	public ProductService productService;
	
	public ProfileController() {
		System.out.println("profile controller bean created");
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
//		User user = (User)request.getSession().getAttribute("user");
//		String msg = profileService.deleteAccount(user);
//		Long id = (Long)request.getSession().getAttribute("id");
		String msg = profileService.deleteAccount(id);
//		request.getSession().invalidate();
		return msg;
	}
	
	@GetMapping("/{id}")
	public ProfileDTO userDetails(@PathVariable Long id) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.userDetails(user);
//		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.userDetails(id);
	}
	
	@PutMapping("/{id}") 
	public String updateDetails(@RequestBody ProfileDTO profile , @PathVariable Long id) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.updateDetails(user,profile);
//		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.updateDetails(id,profile);
	}
	
	@GetMapping("myproducts")
	public List<MyProductDTO> myProducts(@PathVariable Long id) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
//		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.myProducts(id);
	}
	
	@PutMapping("myproducts/{prodId}")
	public String updateProduct(@PathVariable Integer prodId,@RequestBody ProductDTO product,HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.updateProduct(prodId-1,product,id);
	}
	
	@DeleteMapping("myproducts/{prodId}")
	public String deleteProduct(@PathVariable Integer prodId,HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.deleteProduct(prodId-1,id);
	}
	
	@GetMapping("rentedproducts")
	public List<ProductDTO> rentProduct(HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.rentedProducts(id);
	}
	
	@DeleteMapping("returnproducts/{prodId}")
	public String returnProduct(@PathVariable Integer prodId,@RequestBody RatingDTO rating,HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.returnProduct(prodId-1,id,rating);
	}
}
