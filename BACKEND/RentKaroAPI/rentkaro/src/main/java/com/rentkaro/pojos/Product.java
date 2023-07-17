package com.rentkaro.pojos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product extends ProductEntity {

	@Column(name = "product_name", length = 50)
	private String productName;
	@Column(name = "product_description", length = 150)
	private String productDescription;
	//@Column(name = "rating_id", nullable = true)
	@OneToOne @JoinColumn(name = "rating")
	private Rating rating;
	// price is per day rent of product
	@Column(name = "rental_price")
	private Double rentalPrice;
	@Column(name = "is_available")
	private Boolean isAvailable;
	@ManyToOne @JoinColumn(name = "owner")
	private User owner;
	@OneToOne @JoinColumn(name = "renter")
	private User renter;
	@ManyToOne @JoinColumn(name = "category")
	private Category category;
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinTable( name = "product_wishlist",
			 joinColumns = @JoinColumn(name = "Product_ID",referencedColumnName = "productId"),
			 inverseJoinColumns = @JoinColumn(name = "WishList_ID", referencedColumnName = "Id")
			 )
	private Set<WishList> wishList= new HashSet<WishList>();
}


