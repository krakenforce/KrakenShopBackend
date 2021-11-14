package com.krakenforce.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.dtos.ProductDtos;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.security.common.MessageResponse;
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
	
	
	@PostMapping()
	public ResponseEntity<MessageResponse> addProductToFavorite(
			@RequestParam("userId")int userId,
			@RequestParam("productId") int productId){
		try {
			Users user = usersService.getById(userId);
			Product product = productService.getById(productId);
			Set<Product> productSet = new HashSet<Product>();
			productSet.add(product);
			user.setFavoriteProducts(productSet);
			usersService.addUser(user);
			return new ResponseEntity<MessageResponse>(new MessageResponse("add product " + product.getName() + " to " + user.getUsername()), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse(""), new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@DeleteMapping()
	public ResponseEntity<MessageResponse> deleteProductToFavorite(
			@RequestParam("userId")int userId,
			@RequestParam("productId") int productId){
		try {
			Users user = usersService.getById(userId);
			Product product = productService.getById(productId);
			Set<Product> productSet = user.getFavoriteProducts();
			productSet.remove(product);
			usersService.addUser(user);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete product " + product.getName() + " to " + user.getUsername()), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse(""), new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<ProductDtos>> getFavoriteProductByUser(@PathVariable("userId") int userId, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			List<Product> productList = productService.seachFavoriteProductByUser(userId,pageNo, pageSize, sortBy);
			List<ProductDtos> dtosList = new ArrayList<ProductDtos>();
			Set<String> tagStringSet = new HashSet<String>();		
			for(Product item : productList) {	
				ProductDtos dtos = new ProductDtos();
				for (Tag tag : item.getTags()) {
					tagStringSet.add(tag.getName());
				}
				dtos.setProductId(item.getProductId());
				dtos.setName(item.getName());
				dtos.setPrice(item.getPrice());
				dtosList.add(dtos);
			}
			
			
			return new ResponseEntity<List<ProductDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
