package com.krakenforce.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krakenforce.app.model.FileResponseModel;
import com.krakenforce.app.service.FileStorageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
							MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<FileResponseModel> uploadFile(@RequestParam("file") MultipartFile file){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/files/")
				.path(fileName)
				.toUriString();
		FileResponseModel fileResponse = new FileResponseModel(fileName, fileDownloadUri, file.getContentType(),file.getSize());
		return new ResponseEntity<FileResponseModel>(fileResponse, HttpStatus.OK);
	}
	
	@GetMapping("/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			
		}catch(IOException ex) {
			System.out.println("could not determine file type");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
							.contentType(MediaType.parseMediaType(contentType))
							.body(resource);
	}
}
