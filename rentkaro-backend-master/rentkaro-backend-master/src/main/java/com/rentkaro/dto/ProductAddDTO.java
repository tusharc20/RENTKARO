package com.rentkaro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductAddDTO {

	
	
	private String category;
	
	private String imagePath;
	
	private String productDescription;
	
	private String productName;
	
	private Double rentalPrice;
	
	private Boolean isAvailable;
	
	
}
