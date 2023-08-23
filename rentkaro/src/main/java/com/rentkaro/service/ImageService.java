package com.rentkaro.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public String uploadImage(String path,MultipartFile file,Long productId) throws IOException;
}
