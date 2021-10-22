package com.krakenforce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.service.FileStorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestApiController {
	
	@Autowired
	FileStorageService storageService;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content";
	}
	
//	@PostMapping("/upload")
//	public String test(@RequestParam("file") MultipartFile file) {
//		String path =  storageService.save(file);
//		return storageService.loadImage(path);
//		
//	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content";
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board";
	}
	
	@GetMapping("/admin/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccesss(@PathVariable("userId") int userId) {
		return "Admin Board";
	}
}
