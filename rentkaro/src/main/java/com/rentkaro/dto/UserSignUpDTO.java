package com.rentkaro.dto;

import com.rentkaro.pojos.UserAddress;

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
public class UserSignUpDTO {

	private String firstName;
	private String lastName;
	private String userEmail;
	private String userPassword;
	private String userMobileNo;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private Long pincode;
	private String Country;
}
