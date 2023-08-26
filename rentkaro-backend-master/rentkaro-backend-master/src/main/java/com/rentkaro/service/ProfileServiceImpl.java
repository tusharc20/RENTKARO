package com.rentkaro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_excceptions.CustomException;
import com.rentkaro.dto.MyProductDTO;
import com.rentkaro.dto.OrderListDTO;
import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDTO;
import com.rentkaro.dto.RatingDTO;
import com.rentkaro.dto.UpdateProductDTO;
import com.rentkaro.dto.UpdateProfileDTO;
import com.rentkaro.pojos.OrderHistory;
import com.rentkaro.pojos.Product;
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
	public UpdateProfileDTO updateDetails(Long id, UpdateProfileDTO profile) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		user.setFirstName(profile.getFirstName());
		user.setLastName(profile.getLastName());
		UserAddress userAddress = user.getUserAddress();
		userAddress.setAddress1(profile.getAddress1());
		userAddress.setAddress2(profile.getAddress2());
		userAddress.setCity(profile.getCity());
		userAddress.setState(profile.getState());
		userAddress.setPincode(profile.getPincode());
		userAddress.setCountry(profile.getCountry());
		user.setUserAddress(userAddress);
		userRepo.save(user);
		return modelMap.map(user, UpdateProfileDTO.class);
	}

//	@Override
//	public List<MyProductDTO> myProducts(Long id) {
//		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
//		List<MyProductDTO> products = new ArrayList<>();
//		List<Product> productOBJS = user.getOwnedProductList();
//		for (Product productOBJ : productOBJS) {
//			products.add(new MyProductDTO(productOBJ.getProductId(), productOBJ.getOwner().getId(),
//					productOBJ.getProductName(), productOBJ.getProductDescription(), productOBJ.getProductFeatures(),
//					productOBJ.getRentalPrice(), productOBJ.getDeposite(), productOBJ.getCategory(),
//					productOBJ.getRating(), null, productOBJ.getProductComplain()));
//		}
//
//		return products;
//	}
//	
	
	@Override
	public List<MyProductDTO> getOwnedProducts(Long id) {
		try {
			User user = userRepo.findByIdWithOwnedProductList(id)
					.orElseThrow(() -> new RuntimeException("Invalid Id."));

			return user.getOwnedProductList().stream().map(p -> modelMap.map(p, MyProductDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());
		}
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
		productOBJ.setCategory(product.getCategory());
		productRepo.save(productOBJ);
		return "Product Updated Successfully";
	}

	@Override
	public String deleteProduct(int prodId, Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
//		Product productOBJ = user.getOwnedProductList().get(prodId);
		// productRepo.delete(productOBJ);
		if (user.getOwnedProductList().get(prodId).getRenter() == null) {
			user.getOwnedProductList().remove(prodId);
			return "Product Deleted Successfully";
		}
		return "Product alredy Rented by someone! cannot delete";
	}

	@Override
	public List<ProductDTO> rentedProducts(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		List<ProductDTO> products = new ArrayList<>();
		Product productOBJ = user.getRentedProduct();
		if (productOBJ != null)
			products.add(modelMap.map(productOBJ, ProductDTO.class));
//		System.out.println(productOBJ);
		return products;
	}

	@Override
	public List<OrderListDTO> orderHistory(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
		List<OrderListDTO> products = new ArrayList<>();
		List<OrderHistory> productOBJS = user.getOrderList();
		for (OrderHistory productOBJ : productOBJS) {
			products.add(modelMap.map(productOBJ, OrderListDTO.class));
		}

		return products;
	}

	@Override
	public String returnProduct(int prodId, Long id, RatingDTO rating) {
//		User user = userRepo.findById(id).orElseThrow(() -> new CustomException("Invalid user id !!!!!"));
//		Product product = user.getRentedProduct().get(prodId);
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
//		product.setRenter(null);
//		productRepo.save(product);
		return "Product Returned";
	}



//	@Override
//	public List<OrderHistoryDTO> getOrderList(Long id) {
//		try {
//			// This user is fetched to check whether it is persistent or not.
//			User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User does not exist."));
//			User persistentUser = userRepo.findByIdWithOrderList(id)
//					.orElseThrow(() -> new RuntimeException("Invalid Id."));
//			System.err.println(persistentUser.getId());
//			persistentUser.getOrderList().stream().forEach((p) -> System.err.println(p.getProduct().getProductId()));
//			return persistentUser.getOrderList().stream().map(p -> new OrderHistoryDTO(p.getOrderId(),
//					p.getProduct().getProductId(), p.getRenterList().getId(), p.getOrderedDate(), p.getTransactionId(), p.getAmount())).collect(Collectors.toList());
//		} catch (Exception e) {
//			throw new RuntimeException(e.getMessage());
//		}
//	}
	
	@Override
	public String updateOwnedProducts(Long ownerId, UpdateProductDTO productDto) {
		try {
			User user = userRepo.findById(ownerId).orElseThrow(() -> new RuntimeException("Invalid Id."));
			boolean doesUserHasProduct = user.getOwnedProductList().stream()
					.anyMatch(p -> p.getProductId().equals(productDto.getProductId()));
			if (doesUserHasProduct) {
				Product persistentProduct = productRepo.findById(productDto.getProductId())
						.orElseThrow(() -> new RuntimeException("Invalid Id."));

				persistentProduct.setProductName(productDto.getProductName());
				persistentProduct.setProductDescription(productDto.getProductDescription());
				persistentProduct.setRentalPrice(productDto.getRentalPrice());
				persistentProduct.setIsAvailable(productDto.getIsAvailable());
//				persistentProduct.setCategory(productDto.getCategory());
				persistentProduct.setCategory(productDto.getCategory());

				productRepo.save(persistentProduct);

				return "Updation succeed.";
			}
			return "Updation failed.";
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());
		}
	}

}
