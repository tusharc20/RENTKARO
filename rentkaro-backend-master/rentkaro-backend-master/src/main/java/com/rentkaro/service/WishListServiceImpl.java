package com.rentkaro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.User;
import com.rentkaro.pojos.WishList;
import com.rentkaro.repository.ProductRepository;
import com.rentkaro.repository.UserRepository;
import com.rentkaro.repository.WishListRepository;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {

	private WishListRepository wishRepo;

	private ProductRepository productRepo;

	private UserRepository userRepository;

	@Override
	public List<Product> getProductsFromWishList(Long userId) {
//		repo.findA
		return null;
	}

	@Override
	public String addToWishList(Long userId, Long prodId) {
		try {

			User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Invalid user id"));

			System.err.println("dsdsfsd");
			Product product = productRepo.findById(prodId)
					.orElseThrow(() -> new RuntimeException("Invalid product Id"));

			WishList wishList = wishRepo.findById(user.getCart().getId())
					.orElseThrow(() -> new RuntimeException("WishList does'nt exist"));
			product.getWishList().add(wishList);
			wishList.getProductList().add(product);

			wishRepo.save(wishList);

			return "Product added to wishList";
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return "Product not added to wishList";

	}
}
