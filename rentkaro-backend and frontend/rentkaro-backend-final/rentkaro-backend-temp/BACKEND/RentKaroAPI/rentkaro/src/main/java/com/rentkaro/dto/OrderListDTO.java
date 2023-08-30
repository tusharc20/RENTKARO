package com.rentkaro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OrderListDTO {
	private String productName;
	private String productDescription;
	private String productFeatures;
	private Double rentalPrice;
	private Double deposite;
	private LocalDate rentalDate;	
	private LocalDate returnDate;
	private String transactionId;
	private Float rating;
	private byte[] prodImg1;
	private	String imgPath1;
	
	public OrderListDTO(String productName, String productDescription, String productFeatures, Double rentalPrice,
			Double deposite, LocalDate rentalDate, LocalDate returnDate, String transactionId, Float rating,
			String imgPath1) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productFeatures = productFeatures;
		this.rentalPrice = rentalPrice;
		this.deposite = deposite;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.transactionId = transactionId;
		this.rating = rating;
		this.imgPath1 = imgPath1;
	}
	
	
}
