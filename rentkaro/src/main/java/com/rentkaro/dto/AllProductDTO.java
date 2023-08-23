package com.rentkaro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AllProductDTO {
	
	private Long productId;
	
	private String category;
	
	private String imagePath;
	
	private String productDescription;
	
	private String productName;
	
	private Double rentalPrice;
	
	private Float rating;
	

}
