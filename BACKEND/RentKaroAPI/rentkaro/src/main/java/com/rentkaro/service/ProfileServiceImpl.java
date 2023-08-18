package com.rentkaro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.OrderHistoryDTO;
import com.rentkaro.dto.ProductDTO;
import com.rentkaro.dto.ProfileDto;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.User;
import com.rentkaro.repository.ProductRepository;
import com.rentkaro.repository.UserRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String deleteAccount(Long id) {
		if (id != null) {

			userRepo.deleteById(id);
			return "Account Deleted.";
		}
		return "Account Not Deleted.";
	}

	@Override
	public ProfileDto myProfile(Long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));
		ProfileDto profile = modelMapper.map(user, ProfileDto.class);
		return profile;
	}

	@Override
	public String editProfile(ProfileDto profiledto) {

		try {
			System.err.println("hgggghghghgghg" + profiledto.getId());
			User user = userRepo.findById(profiledto.getId()).orElseThrow(() -> new RuntimeException("Invalid Id."));
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
			userRepo.save(user);
			return "Profile Updated";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<OrderHistoryDTO> getOrderList(Long id) {
		try {
			User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

			System.err.println("hdhhdsj");
//			List<OrderHistory> orders = user.getOrderList();
			User persistentUser= userRepo.findByIdWithOrderList(id)
					.orElseThrow(() -> new RuntimeException("Invalid Id."));
			;
			return persistentUser.getOrderList().stream().map(p -> modelMapper.map(p, OrderHistoryDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<ProductDTO> getOwnedProducts(Long id) {
		try {
			User user = userRepo.findByIdWithOwnedProductList(id)
					.orElseThrow(() -> new RuntimeException("Invalid Id."));

			return user.getOwnedProductList().stream().map(p -> modelMapper.map(p, ProductDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String updateOwnedProducts(Long ownerId, ProductDTO productDto) {
		try {
			User user = userRepo.findById(ownerId).orElseThrow(() -> new RuntimeException("Invalid Id."));
			if (user.getOwnedProductList().contains(productDto)) {
				System.err.println("dtddttd");
				Product persistentProduct = productRepo.findById(productDto.getProductId())
						.orElseThrow(() -> new RuntimeException("Invalid Id."));
				persistentProduct.setProductName(productDto.getProductName());
				persistentProduct.setProductDescription(productDto.getProductDescription());
				persistentProduct.setRentalPrice(productDto.getRentalPrice());
				persistentProduct.setIsAvailable(productDto.getIsAvailable());
				persistentProduct.setCategory(productDto.getCategory());
				productRepo.save(persistentProduct);
			}

//			List<Product> products = user.getOwnedProductList();
			return "Updation failed.";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String deleteProductFromOwnedProducts(Long id, Long productId) {
		try {
			User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id."));

//			List<Product> products = user.getOwnedProductList();
			user.getOwnedProductList().removeIf((i) -> i.getProductId().equals(productId));
			userRepo.save(user);
			return "Product Removed.";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
