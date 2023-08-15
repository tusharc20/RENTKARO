package com.rentkaro.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.rentkaro.pojos.OrderHistory;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.UserAddress;
import com.rentkaro.pojos.WishList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDto {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userEmail;
	private String userMobileNo;
	
	private UserAddress userAddress;
	 
//	private List<Product> ownedProductList = new ArrayList<Product>();
	
//	private Product rentedProduct ;
	
	
//	private WishList cart;
	
//	List<OrderHistory> orderList = new ArrayList<OrderHistory>();
	
//	private LocalDate rentalDate;
	
//	private LocalDate returnDate;
}
