package com.rentkaro.service;

import java.util.List;

import com.rentkaro.pojos.Product;


public interface WishListService {

	List<Product> getProductsFromWishList(Long userId);
	
	String addToWishList(Long userId,Long prodId);
}
