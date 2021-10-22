package com.krakenforce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.service.ProductService;
import com.krakenforce.app.service.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/favorite")
public class FavoriteProductController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ProductService productService;
	

}
