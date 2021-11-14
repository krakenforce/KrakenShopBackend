package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ProductGameCode;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.ProductGameCodeService;
import com.krakenforce.app.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/game_code")
public class ProductGameCodeController {

	@Autowired
	ProductGameCodeService productGameCodeService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping()
	public ResponseEntity<MessageResponse> add(@RequestParam("productId") int productId,
			@RequestBody ProductGameCode gameCode){
		try {
			Product product = productService.getById(productId);
			gameCode.setProduct(product);
			Date date = new Date();
			Timestamp timestamp2 = new Timestamp(date.getTime());
			gameCode.setCreatedAt(timestamp2);
			productGameCodeService.add(gameCode);
			return new ResponseEntity<MessageResponse>(new MessageResponse("add success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<MessageResponse>(new MessageResponse("add fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{gameCodeId}")
	public ResponseEntity<MessageResponse> delete(@PathVariable("gameCodeId") int gameCodeId){
		try {
			productGameCodeService.delete(gameCodeId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{gameCodeId}")
	public ResponseEntity<ProductGameCode> add(@PathVariable("gameCodeId") int gameCodeId){
		try {
			ProductGameCode gameCode = productGameCodeService.getById(gameCodeId);
			return new ResponseEntity<ProductGameCode>(gameCode, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<ProductGameCode>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/search/")
	public ResponseEntity<Map<String, Object>> getByProduct(@RequestParam("productId") int productId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productGameCodeService.getGameCodeByProduct(productId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_wallet")
	public ResponseEntity<Map<String, Object>> getByWallet(@RequestParam("walletId") int walletId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productGameCodeService.getGameCodeByWallet(walletId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_status/")
	public ResponseEntity<List<ProductGameCode>> getByProductAndStatus(@RequestParam("productId") int productId,
			@RequestParam("status") boolean status,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			List<ProductGameCode> list = productGameCodeService.getGameCodeByProductAndStatus(productId,status, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductGameCode>>(list, new HttpHeaders(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<ProductGameCode>>(null, new HttpHeaders(),HttpStatus.OK);
		}
	}
	
	@GetMapping("/inventory_pro")
	public ResponseEntity<Long> countCodeByProduct(@RequestParam("productId") int productId){
		try {
			long amount = productGameCodeService.getCodeAmountByProduct(productId);
			return new ResponseEntity<Long>(amount, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Long>((long) 0, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/inventory_prostat")
	public ResponseEntity<?> countCodeByProduct(@RequestParam("productId") int productId,
			@RequestParam("status") boolean status){
		try {
			long amount = productGameCodeService.getCodeAmountByProductAndStatus(productId, status);
			return new ResponseEntity<Long>(amount, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Long>((long) 0, new HttpHeaders(), HttpStatus.OK);
		}
	}
}
