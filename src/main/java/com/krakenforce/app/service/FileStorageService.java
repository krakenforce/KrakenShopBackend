package com.krakenforce.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.krakenforce.app.exception.FileStorageException;
import com.krakenforce.app.exception.MyFileNotFoundException;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation = Paths.get("uploads");
	
	public void init() {
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch(Exception ex) {
			throw new FileStorageException("Could not create the directory to upload");
		}
		
	}
	
	/**
	 * use to store file
	 * @param file
	 * @return fileName
	 */
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch(IOException ex) {
			throw new FileStorageException("Could not store the file" + fileName + ". Please try again", ex);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new MyFileNotFoundException("File not found" + fileName);
			}
		}catch(MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found" + fileName);
		}
	}
}
