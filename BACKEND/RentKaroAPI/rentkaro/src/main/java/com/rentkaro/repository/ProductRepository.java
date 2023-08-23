package com.rentkaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentkaro.pojos.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	
		
}
