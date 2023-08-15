package com.rentkaro.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.ProfileDto;
import com.rentkaro.pojos.OrderHistory;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.User;
import com.rentkaro.repository.ProductRepository;
import com.rentkaro.repository.UserRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserRepository UserRepo;
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String deleteAccount(Long id) {
		if (id != null) {

			UserRepo.deleteById(id);
			return "Account Deleted.";
		}
		return "Account Not Deleted.";
	}

	@Override
	public ProfileDto myProfile(Long id) {

		User user = UserRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));
		ProfileDto profile = modelMapper.map(user, ProfileDto.class);
		return profile;
	}

	@Override
	public String editProfile(ProfileDto profiledto) {

		try {
			System.err.println("hgggghghghgghg" + profiledto.getId());
			User user = UserRepo.findById(profiledto.getId()).orElseThrow(() -> new RuntimeException("Invalid Id."));
			System.err.println("hgggghghghgghg");
			user.setFirstName(profiledto.getFirstName());
			user.setLastName(profiledto.getLastName());
			user.setUserEmail(profiledto.getUserEmail());
			user.setUserMobileNo(profiledto.getUserMobileNo());
			user.getUserAddress().setAddress1(profiledto.getUserAddress().getAddress1());
			user.getUserAddress().setAddress2(profiledto.getUserAddress().getAddress2());
			user.getUserAddress().setCity(profiledto.getUserAddress().getCity());
			user.getUserAddress().setCountry(profiledto.getUserAddress().getCountry());
			user.getUserAddress().setPincode(profiledto.getUserAddress().getPincode());
			user.getUserAddress().setState(profiledto.getUserAddress().getState());
			UserRepo.save(user);
			return "Profile Updated";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
//		return "Profile Updation failed.";
	}

	@Override
	public List<OrderHistory> getOrderList(Long id) {
		try {
			User user = UserRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

			System.err.println("hdhhdsj");
			List<OrderHistory> orders = user.getOrderList();
//			List<OrderHistory> orders = UserRepo.findByIdWithOrderList(id)
//					.orElseThrow(() -> new RuntimeException("Invalid Id."));
//			;
			return orders;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Product> getOwnedProducts(Long id) {
		try {
			User user = UserRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

			List<Product> products = user.getOwnedProductList();
			System.err.println(products);
			return products;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Product> updateOwnedProducts(Long id) {
		try {
			User user = UserRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

			List<Product> products = user.getOwnedProductList();
			return products;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public String deleteProductFromOwnedProducts(Long id, Long productId) {
		try {
			User user = UserRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

//			List<Product> products = user.getOwnedProductList();
			user.getOwnedProductList().removeIf((i) -> i.getProductId().equals(productId));
			UserRepo.save(user);
			return "Product Removed.";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	

}
