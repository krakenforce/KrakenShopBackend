package com.krakenforce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.krakenforce.app.model.Users;
import com.krakenforce.app.repository.UsersRepository;
import com.krakenforce.app.security.common.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	UsersRepository usersRepository;
	
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Users>> getAllUser(){
		List<Users> users = usersRepository.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Users> getUserById(@PathVariable("userId")int userId){
		Users user = usersRepository.findById(userId).orElse(null);
		if(user != null) {
			return ResponseEntity.ok(user);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping()
	//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUserInfo(@RequestBody Users user, 
			@RequestParam("avatar") MultipartFile avatar) {
		
		Users selectedUser = usersRepository.findById(user.getUserId()).orElse(null);
		if(selectedUser != null) {
			selectedUser = user;
			usersRepository.save(selectedUser);
			return ResponseEntity.ok(selectedUser);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Not Found user"));
		}
	}
	
	@DeleteMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") int userId){
		usersRepository.deleteById(userId);
		return ResponseEntity.ok(new MessageResponse("Delete user successfully"));
	}
	
	
}
