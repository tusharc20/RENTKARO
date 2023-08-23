package com.rentkaro.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.AllProductDTO;
import com.rentkaro.pojos.Category;
import com.rentkaro.pojos.Product;
import com.rentkaro.repository.ProductOrCategoryRepository;

@Service
@Transactional
public class ProductOrCategoryServiceImpl implements ProductOrCategoryService {

	@Autowired 
	private ModelMapper mapper;
	
	@Autowired
	private ProductOrCategoryRepository productRepo;
	
	
	@Override
	public List<AllProductDTO> getProducts(String productDescription) {
		
		List<Product>products=new ArrayList<>();
		
		List<AllProductDTO>selectedProducts=new ArrayList<>();
		
	products=productRepo.getProducts();
	
	int i=0;
	
	for(Product p :products) {
		if((p.getProductName().toLowerCase()).contains(productDescription.toLowerCase())) {
			
			selectedProducts.add(mapper.map(p, AllProductDTO.class));
			// selectedProducts.get(i).setRating(p.getRating().getRating());
	    	// i++;
			
		}else if((p.getProductDescription().toLowerCase()).contains(productDescription.toLowerCase())) {
			
			selectedProducts.add(mapper.map(p, AllProductDTO.class));
			
		}
		else if(p.getCategory().equals(Category.valueOf(productDescription.toUpperCase()))) {
			
			selectedProducts.add(mapper.map(p, AllProductDTO.class));
			// selectedProducts.get(i).setRating(p.getRating().getRating());
	    	// i++;
			
		}
	}

		return selectedProducts;
	
	}

	
}
