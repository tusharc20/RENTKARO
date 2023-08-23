package com.rentkaro.service;

import java.util.List;

import com.rentkaro.dto.AllProductDTO;
import com.rentkaro.pojos.Category;

public interface CategoryService {

	public List<AllProductDTO>getProductsByCategory(Category productCategory);
}
