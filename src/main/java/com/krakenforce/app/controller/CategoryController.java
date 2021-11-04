package com.krakenforce.app.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.dtos.CategoryDtos;
import com.krakenforce.app.model.Category;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.CategoryService;

@CrossOrigin("http://localhost:4000/")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping()
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		try {
			Category result = categoryService.add(category);
			return new ResponseEntity<Category>(result, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Category>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping()
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		try {
			Category result = categoryService.update(category);
			return new ResponseEntity<Category>(result, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Category>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<MessageResponse> deleteCategory(@PathVariable("categoryId") int categoryId){
		try {
			categoryService.delete(categoryId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<List<CategoryDtos>> getAll(){
		try {
			List<CategoryDtos> list = categoryService.getAll();
			return new ResponseEntity<List<CategoryDtos>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CategoryDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllCategory(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="categoryId") String sortBy){
		try {
			Map<String, Object> response = categoryService.getAllCategory(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") int categoryId){
		try {
			Category category = categoryService.getById(categoryId);
			return new ResponseEntity<Category>(category, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Category>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, Object>> getAllCategoryByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="category_id") String sortBy){
		try {
			Map<String, Object> response = categoryService.getAllCategoryByName(keyword, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
