package com.rentkaro.service;

import java.util.List;

import com.rentkaro.dto.ProfileDto;
import com.rentkaro.pojos.OrderHistory;
import com.rentkaro.pojos.Product;

public interface ProfileService {
	
	String deleteAccount(Long id);
	ProfileDto myProfile(Long id);
	String editProfile(ProfileDto profiledto);
	List<OrderHistory> getOrderList(Long id);
	List<Product> getOwnedProducts(Long id);
	String deleteProductFromOwnedProducts(Long id, Long productId);
	List<Product> updateOwnedProducts(Long id);
}
