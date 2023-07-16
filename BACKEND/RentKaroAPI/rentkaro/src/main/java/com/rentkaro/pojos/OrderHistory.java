package com.rentkaro.pojos;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class OrderHistory  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@OneToOne(cascade = CascadeType.ALL)@JoinColumn(name = "product_id")
	private Product product;
	@OneToOne(cascade = CascadeType.ALL)@JoinColumn(name = "renter")
	private User renter;
	private LocalDate orderedDate;
	private Long transactionId;
	private Double amount;  //no. of days to rent * product price rent/ day
	
}
