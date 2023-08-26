package com.rentkaro.service;

import java.util.List;
import java.util.Locale.Category;

import com.rentkaro.dto.AllProductDTO;

public interface CategoryService {

	public List<AllProductDTO>getProductsByCategory(Category productCategory);
}
