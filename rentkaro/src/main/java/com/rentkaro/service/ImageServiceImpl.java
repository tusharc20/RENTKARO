package com.rentkaro.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rentkaro.pojos.Product;
import com.rentkaro.repository.ProductRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public String uploadImage(String path,MultipartFile file,Long productId) throws IOException {
	
		
		//file name
		
		String name=file.getOriginalFilename();
		
		
		//random name generator 
		
		 String randomId=UUID.randomUUID().toString();
		 
		String fileName1= randomId.concat(name.substring(name.lastIndexOf(".")));
		
		
		//fullpath
		
		 String filePath=path + File.separator + fileName1;
		
		
		
		//create folder if not created
		 
		 File f=new File(path);
		 
		 if(!f.exists()) {
			 
			 f.mkdir();
		 }
		
		//copy to folder
		 
		 Files.copy(file.getInputStream(), Paths.get(filePath));
		 
		Product p= productRepo.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));
		 
		 p.setImagePath(filePath);
		 
		 productRepo.save(p);
		
		return name;
		
	}
}
