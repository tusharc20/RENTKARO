package com.rentkaro.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentkaro.dto.ApiResponse;
import com.rentkaro.service.ImageService;

@RestController
@RequestMapping
public class ImageController {
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private ImageService imageServ;
	
	@PostMapping("/products/add/image/{productId}")
	public ResponseEntity<?> addImage(@RequestParam("image") MultipartFile image,@PathVariable Long productId ){

	try {
		
		
		
		String fileName=imageServ.uploadImage(path, image,productId);
		
		
		  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to add image"));
	}
	
	

	return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Image added successfully"));
	}
	
	
	@GetMapping(value="/products/serve/image/{imageName}" , produces=MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable String imageName,HttpServletResponse response) throws IOException{
		
		InputStream resource=imageServ.getImage(path,imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
