package com.rentkaro.dto;

import java.util.List;

import com.rentkaro.pojos.Categories;
import com.rentkaro.pojos.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyProductDTO {
	private String productName;
//	private String imgPath1;
//	private String imgPath2;
	private String productDescription;
	private String productFeatures;
	private Double rentalPrice;
	private Double deposite;
	private Categories category;
	private Float rating;
	private List<String> review;
	private User renter;
	private int productComplain;
	
	public MyProductDTO(String productName, String productDescription, String productFeatures, Double rentalPrice,
			Double deposite, String category,Float rating,List<String> review,User renter,int productComplain) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productFeatures = productFeatures;
		this.rentalPrice = rentalPrice;
		this.deposite = deposite;
		this.category = Categories.valueOf(category);
		this.rating = rating;
		this.review = review;
		this.renter = renter;
		this.productComplain = productComplain;
	}

}
