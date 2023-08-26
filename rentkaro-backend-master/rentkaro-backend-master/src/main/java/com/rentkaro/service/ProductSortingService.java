package com.rentkaro.service;

import java.util.List;

import com.rentkaro.dto.AllProductDTO;

public interface ProductSortingService {

	public List<AllProductDTO>getAllProductsInSortedManner(String criteria);
}
