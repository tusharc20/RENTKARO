package com.rentkaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentkaro.dto.ApiResponse;
import com.rentkaro.service.WishListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	@PostMapping("/add")
	public ResponseEntity<?> getAllPRoductDetails(@RequestBody Long userId, @RequestBody Long prodId) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(wishListService.addToWishList(userId, prodId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}

	}

}
