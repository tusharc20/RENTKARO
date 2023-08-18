package com.rentkaro.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rentkaro.pojos.Category;
import com.rentkaro.pojos.Product;
import com.rentkaro.pojos.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long productId;
	private String productName;
	private String productDescription;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Rating rating;
	private Double rentalPrice;
	private Boolean isAvailable;
//	private String ownerName;
//	private User owner;
//	private User renter;
	private Category category;

	public Long getProductId() {
		return productId;
	}


}
