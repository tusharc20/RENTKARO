package com.rentkaro.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rentkaro.dto.ProductAddDTO;

public interface ProductService {

	public ResponseEntity<?> addProduct(ProductAddDTO productDTO,Long userId);
	
	
}
