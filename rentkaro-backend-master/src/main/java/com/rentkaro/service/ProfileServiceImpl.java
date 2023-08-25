package com.rentkaro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excceptions.CustomException;
import com.rentkaro.dto.MyProductDTO;
import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.dto.RatingDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.Rating;
import com.rentkaro.pojos.User;
import com.rentkaro.pojos.UserAddress;
import com.rentkaro.repository.ProductRepository;
import com.rentkaro.repository.UserRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	public UserRepository userRepo;
	@Autowired
	public ModelMapper modelMap;
	@Autowired
	public ProductRepository productRepo;

	@Override
	public String deleteAccount(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		userRepo.delete(user);
		return "User " + user.getFirstName() + " " + user.getLastName() + " details deleted!";
	}

	@Override
	public ProfileDTO userDetails(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		return new ProfileDTO(user.getFirstName(), user.getLastName(), user.getUserEmail(), user.getUserMobileNo(),
				user.getUserAddress().getAddress1(), user.getUserAddress().getAddress2(),
				user.getUserAddress().getCity(), user.getUserAddress().getState(), user.getUserAddress().getPincode(),
				user.getUserAddress().getCountry());
	}

	@Override
	public String updateDetails(Long id, ProfileDTO profile) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		user.setFirstName(profile.getFirstName());
		user.setLastName(profile.getLastName());
		user.setUserEmail(profile.getUserEmail());
		user.setUserMobileNo(profile.getUserMobileNo());
		UserAddress userAddress = user.getUserAddress();
		userAddress.setAddress1(profile.getAddress1());
		userAddress.setAddress2(profile.getAddress2());
		userAddress.setCity(profile.getCity());
		userAddress.setState(profile.getState());
		userAddress.setPincode(profile.getPincode());
		userAddress.setCountry(profile.getCountry());
		user.setUserAddress(userAddress);
		userRepo.save(user);
		return "Profile Successfully Updated";
	}

	@Override
	public List<MyProductDTO> myProducts(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		List<MyProductDTO> products = new ArrayList<>();
		List<Product> productOBJS = user.getOwnedProductList();
		for (Product productOBJ : productOBJS) {
			products.add(modelMap.map(productOBJ, MyProductDTO.class));
		}
		return products;
	}

	@Override
	public String updateProduct(int prodId, ProductDTO product, Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		Product productOBJ = user.getOwnedProductList().get(prodId);
		productOBJ.setProductName(product.getProductName());
		productOBJ.setProductDescription(product.getProductDescription());
		productOBJ.setProductFeatures(product.getProductFeatures());
		productOBJ.setRentalPrice(product.getRentalPrice());
		productOBJ.setDeposite(product.getDeposite());
		productOBJ.setCategory(product.getCategory().toString());
		productRepo.save(productOBJ);
		return "Product Updated Successfully";
	}

	@Override
	public String deleteProduct(int prodId, Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
//		Product productOBJ = user.getOwnedProductList().get(prodId);
		user.getOwnedProductList().remove(prodId);
//		productRepo.delete(productOBJ);
		return "Product Deleted Successfully";
	}
	
	@Override
	public List<ProductDTO> rentedProducts(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		List<ProductDTO> products = new ArrayList<>();
		List<Product> productOBJS = user.getRentedProduct();
		for (Product productOBJ : productOBJS) {
			products.add(modelMap.map(productOBJ, ProductDTO.class));
		}
		return products;
	}

	@Override
	public String returnProduct(int prodId, Long id,RatingDTO rating) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		Product product = user.getRentedProduct().get(prodId);
//		if(product.getRating()==null) {
//			product.setRating(new Rating(product,user,rating.getRating(),rating.getReview()));
//		}
//		else {
//			Rating ratings = product.getRating();
//			ratings.setRenter(user);
//			ratings.setRating(rating.getRating());
//			ratings.setReview(rating.getReview());
//			product.setRating(ratings);
//		}
		product.setRenter(null);
		productRepo.save(product);
		return "Product Returned";
	}
	
	

}
