package com.rentkaro.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Required;

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
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	private String userEmail;
	
//	@Pattern(message = "password must be in valid format",regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String userPassword;
	

	private String userMobileNo;
	@NotNull
	private String address1;
	private String address2;
	@NotNull
	private String city;
	@NotNull
	private String state;

	private Long pincode;
	@NotNull
	private String Country;
}
