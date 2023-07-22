package com.rentkaro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentkaro.dto.ProductDTO;

@Service
@org.springframework.transaction.annotation.Transactional
public interface ProductService {
	String addProduct(Long id,ProductDTO obj);

	List<ProductDTO> displayProducts(Long id);

	List<ProductDTO> displayProductsByCategory(Long id,String category);
}
