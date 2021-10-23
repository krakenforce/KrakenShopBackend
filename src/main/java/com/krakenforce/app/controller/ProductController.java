package com.krakenforce.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krakenforce.app.dtos.ProductDtos;
import com.krakenforce.app.model.Category;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.security.common.ProductResponse;
import com.krakenforce.app.service.CategoryService;
import com.krakenforce.app.service.FileStorageService;
import com.krakenforce.app.service.ProductService;
import com.krakenforce.app.service.TagService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TagService tagService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("product") ProductResponse productResponse
			,@RequestPart("thumbnailImage") MultipartFile thumbnailImage){
		try {
			Set<Integer> categoryIdSet = productResponse.getCategoryIdSet();
			Set<Category> categories = new HashSet<Category>();
			for(Integer categoryId : categoryIdSet) {
				Category category = categoryService.getById(categoryId);
				categories.add(category);
			}
			Set<Integer> tagIdSet = productResponse.getTagIdSet();
			Set<Tag> tags = new HashSet<Tag>();
			for(Integer tagId : tagIdSet) {
				Tag tag = tagService.getById(tagId);
				tags.add(tag);
			}
			Product product = productResponse.getProduct();
			
			if(thumbnailImage != null) {
				product.setThumbnailImageUrl(getImagePath(thumbnailImage));
			}
			product.setTags(tags);
			product.setCategories(categories);
			
			productService.save(product);
			return new ResponseEntity<Product>(product, new HttpHeaders(), HttpStatus.OK);
			
		}catch(Exception ex) {
			return new ResponseEntity<Product>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	/*use to get Image path when upload*/
	public String getImagePath(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/files/")
				.path(fileName)
				.toUriString();
		return fileDownloadUri;
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductDtos>> getAllProduct(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			List<Product> productList = productService.getAllProduct(pageNo, pageSize, sortBy);
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
				dtos.setTagName(tagStringSet);
				dtosList.add(dtos);
			}
			
			
			return new ResponseEntity<List<ProductDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
