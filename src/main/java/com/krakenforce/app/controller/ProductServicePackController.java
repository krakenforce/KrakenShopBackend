package com.krakenforce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.ProductServicePack;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.ProductServicePackService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product_service_pack")
public class ProductServicePackController {
	
	@Autowired
	ProductServicePackService productServicePackService;
	
	@PostMapping()
	public ResponseEntity<ProductServicePack> addProductServicePack(@RequestBody ProductServicePack productServicePack){
		try {
			ProductServicePack servicePack = productServicePackService.add(productServicePack);
			return new ResponseEntity<ProductServicePack>(servicePack, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<ProductServicePack>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{servicePackId}")
	public ResponseEntity<MessageResponse> deleteProductServicePack(@PathVariable("servicePackId") int servicePackId){
		try {
			productServicePackService.delete(servicePackId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{servicePackId}")
	public ResponseEntity<ProductServicePack> getProductServicePackById(@PathVariable("servicePackId") int servicePackId){
		try {
			ProductServicePack servicePack = productServicePackService.getById(servicePackId);
			return new ResponseEntity<ProductServicePack>(servicePack, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<ProductServicePack>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductServicePack>> getAllServicePack(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<ProductServicePack> servicePackList = productServicePackService.getAllProductServicePacks(pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductServicePack>>(servicePackList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductServicePack>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductServicePack>> getServicePackByName(
			@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<ProductServicePack> servicePackList = productServicePackService.getProductServicePacksByName(keyword,pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductServicePack>>(servicePackList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductServicePack>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
}
