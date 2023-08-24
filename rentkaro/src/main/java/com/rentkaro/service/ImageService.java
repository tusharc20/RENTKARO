package com.rentkaro.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public String uploadImage(String path,MultipartFile file,Long productId) throws IOException;

	public InputStream getImage(String path, String imageName) throws FileNotFoundException ;
}
