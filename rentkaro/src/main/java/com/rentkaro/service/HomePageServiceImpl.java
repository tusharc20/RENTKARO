package com.rentkaro.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentkaro.dto.AllProductDTO;
import com.rentkaro.pojos.Product;
import com.rentkaro.repository.HomePageRepository;

@Service
@Transactional
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired 
	private HomePageRepository homeRepo;
	
	@Override
	public List<AllProductDTO> getAllProductDetails() {
	
     List<AllProductDTO> allProductsDTOList=new ArrayList<>();
     
     List<Product>allProductsList=homeRepo.getAllProductDetails();
     
     int i=0;
     for(Product p : allProductsList) {
    	 
    	 allProductsDTOList.add(mapper.map(p, AllProductDTO.class));
    	// allProductsDTOList.get(i).setRating(p.getRating().getRating());
    	// i++;
     }
		
		return allProductsDTOList;
	}

}
