package com.rentkaro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.AllProductDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.repository.ProductSortingRepository;

@Service
@Transactional
public class ProductSortingServiceImpl implements ProductSortingService {

	@Autowired
	private ProductSortingRepository productRepo;
	
	@Autowired 
	private ModelMapper mapper;
	
	
	@Override
	public List<AllProductDTO> getAllProductsInSortedManner(String criteria) {
		
		List<Product>products=new ArrayList<>();
		
		List<Product>sortedList=new ArrayList<>();
		
		products=productRepo.getAllProducts();
		
		if(criteria.equalsIgnoreCase("rentalPrice")) {
			
			sortedList=products.stream().sorted((a,b)->a.getRentalPrice().compareTo(b.getRentalPrice())).collect(Collectors.toList());
		
		}else if(criteria.equalsIgnoreCase("rating")) {
			
			sortedList=products.stream().sorted((a,b)->b.getRating().compareTo(a.getRating())).collect(Collectors.toList());
		
		}
		
		
		List<AllProductDTO>productList=new ArrayList<>();
		
		int i=0;
		
		for(Product p:sortedList) {
			
			productList.add(mapper.map(p, AllProductDTO.class));
			
			// productList.get(i).setRating(p.getRating().getRating());
	    	// i++;
			
		}
		
		return productList;
	}

}
