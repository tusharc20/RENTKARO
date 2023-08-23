package com.rentkaro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentkaro.pojos.Category;
import com.rentkaro.pojos.Category;
import com.rentkaro.pojos.Category;
import com.rentkaro.pojos.Product;

public interface CategoryRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p where p.category=?1")
	public List<Product>getProductsByCategory(Category productCategory);

}
