package com.rentkaro.service;

import java.util.List;

import com.rentkaro.dto.AllProductDTO;

public interface ProductOrCategoryService {

	public List<AllProductDTO>getProducts(String productDescription);
}
