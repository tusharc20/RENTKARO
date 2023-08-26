package com.rentkaro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.AllProductDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired 
	private CategoryRepository catRepo;
	
	@Override
	public List<AllProductDTO> getProductsByCategory(Category productCategory) {
		
		
		
	     List<AllProductDTO> allProductsDTOList=new ArrayList<>();
	     
	     List<Product>allProductsList=catRepo.getProductsByCategory(productCategory);
	     
	     int i=0;
	     for(Product p : allProductsList) {
	    	 
	    	 allProductsDTOList.add(mapper.map(p, AllProductDTO.class));
	    	// allProductsDTOList.get(i).setRating(p.getRating().getRating());
	    	// i++;
	     }
			
			return allProductsDTOList;
		}
	
	

}
