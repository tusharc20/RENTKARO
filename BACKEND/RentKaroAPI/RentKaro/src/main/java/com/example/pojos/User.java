package com.example.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends UserEntity{


	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPassword;
	private String userMobileNo;
	private UserAddress userAddress;
	private List<Product> ownedProductList = new ArrayList<Product>();
	private List<Product> rentedProductList = new ArrayList<Product>();
	private LocalDate rentalDate;
	private LocalDate returnDate;
	
	
}
