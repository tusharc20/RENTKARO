package com.rentkaro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentkaro.pojos.Product;

public interface ProductSortingRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p")
	public List<Product>getAllProducts();
}
