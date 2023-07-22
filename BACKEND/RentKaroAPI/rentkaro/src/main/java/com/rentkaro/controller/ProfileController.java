package com.rentkaro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.pojos.User;
import com.rentkaro.service.ProductService;
import com.rentkaro.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	public ProfileService profileService;
	@Autowired
	public ProductService productService;
	
	public ProfileController() {
		System.out.println("profile controller bean created");
	}

	@DeleteMapping
	public String deleteUser(HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		String msg = profileService.deleteAccount(user);
		Long id = (Long)request.getSession().getAttribute("id");
		String msg = profileService.deleteAccount(id);
		request.getSession().invalidate();
		return msg;
	}
	
	@GetMapping
	public ProfileDTO userDetails(HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.userDetails(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.userDetails(id);
	}
	
	@PutMapping 
	public String updateDetails(@RequestBody ProfileDTO profile , HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.updateDetails(user,profile);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.updateDetails(id,profile);
	}
	
	@GetMapping("myproducts")
	public List<ProductDTO> myProducts(HttpServletRequest request) {
//		User user = (User)request.getSession().getAttribute("user");
//		return profileService.myProducts(user);
		Long id = (Long)request.getSession().getAttribute("id");
		return profileService.myProducts(id);
	}
}
