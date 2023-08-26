package com.rentkaro.dto;

import com.rentkaro.pojos.Categories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {
	private Long productId;
	private String productName;
//	private String imgPath1;
//	private String imgPath2;
	private String productDescription;
	private String productFeatures;
	private Double rentalPrice;
	private Double deposite;
	private Categories category;
	private Float rating;
	
//	public ProductDTO(Long prodId, String productName, String productDescription, String productFeatures, Double rentalPrice,
//			Double deposite, String category,Float rating) {
//		super();
//		this.productId = prodId;
//		this.productName = productName;
//		this.productDescription = productDescription;
//		this.productFeatures = productFeatures;
//		this.rentalPrice = rentalPrice;
//		this.deposite = deposite;
//		this.category = Categories.valueOf(category);
//		this.rating = rating;
//	}
	
	
}
