package com.krakenforce.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.UserVipClass;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.UserVipClassService;
import com.krakenforce.app.service.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user_vip")
public class UserVipClassController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private UserVipClassService userVipClassService;

	@PutMapping()
	public ResponseEntity<MessageResponse> addVipToUser(@RequestParam("userId") int userId,
			@RequestParam("vipClassId") int vipClassId) {
		try {
			Users user = usersService.getById(userId);
			UserVipClass userVipClass = userVipClassService.getById(vipClassId);

			user.setUserVipClass(userVipClass);
			usersService.addUser(user);
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("add " + userVipClass.getClassName() + " to user: " + user.getEmail()),
					new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<MessageResponse>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllVipClass(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> response = userVipClassService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String,Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String,Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/{vipClassId}")
	public ResponseEntity<Map<String, Object>> getUserByVipClass(@PathVariable("vipClassId") int vipClassId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "user_id") String sortBy) {
		try {
			Map<String, Object> response = usersService.getUserByVipClass(vipClassId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String ,Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}
}
