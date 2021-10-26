package com.krakenforce.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krakenforce.app.dtos.ProductDtos;
import com.krakenforce.app.model.Category;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ProductComment;
import com.krakenforce.app.model.ProductImage;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.security.common.ProductResponse;
import com.krakenforce.app.service.CategoryService;
import com.krakenforce.app.service.FileStorageService;
import com.krakenforce.app.service.ProductCommentService;
import com.krakenforce.app.service.ProductImageService;
import com.krakenforce.app.service.ProductService;
import com.krakenforce.app.service.TagService;
import com.krakenforce.app.service.UsersService;

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
	ProductImageService productImageService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ProductCommentService productCommentService;
	
	//==================================================================================
	// PRODUCT MODULE
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("product") ProductResponse productResponse
			,@RequestPart("thumbnailImage") MultipartFile thumbnailImage,
			@RequestPart("imageList") List<MultipartFile> imageList){
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
			
			if(imageList != null) {
				for(int i = 0; i < imageList.size(); i++ ) {
					ProductImage newImage = new ProductImage();
					newImage.setProduct(product);
					newImage.setImageUrl(getImagePath(imageList.get(i)));
					newImage.setPriority(i + 1);
					productImageService.add(newImage);
				}
			}
			
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
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("productId") int productId){
		try {
			productService.delete(productId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
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
	
	@GetMapping("/search/priceRange")
	public ResponseEntity<List<ProductDtos>> getProductByPriceRange(@RequestParam(name = "startPrice", defaultValue = "0") float startPrice,
			@RequestParam("endPrice") float endPrice,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		
		try {
			List<Product> productList = productService.getAllProductPriceRange(startPrice, endPrice,pageNo, pageSize, sortBy);
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
	
	@GetMapping("/search/name")
	public ResponseEntity<List<ProductDtos>> getProductByName(@RequestParam("productName") String productName,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		
		try {
			List<Product> productList = productService.seachProductByName(productName,pageNo, pageSize, sortBy);
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
	
	@GetMapping("/search/service_pack")
	public ResponseEntity<List<ProductDtos>> getProductByName(@RequestParam("servicePackId") int servicePackId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		
		try {
			List<Product> productList = productService.seachProductByServicePack(servicePackId,pageNo, pageSize, sortBy);
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
	
	@GetMapping("/search/below")
	public ResponseEntity<List<ProductDtos>> getProductBelowPrice(
			@RequestParam("searchPrice") float searchPrice,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		
		try {
			List<Product> productList = productService.getAllProductBelowPrice(searchPrice,pageNo, pageSize, sortBy);
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
	
	@GetMapping("/tag/{tagId}")
	public ResponseEntity<List<ProductDtos>> getProductByTag(@PathVariable("tagId") int tagId, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			List<Product> productList = productService.seachProductByTag(tagId,pageNo, pageSize, sortBy);
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
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDtos>> getProductByCategory(@PathVariable("categoryId") int categoryId, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			List<Product> productList = productService.seachProductByCategory(categoryId,pageNo, pageSize, sortBy);
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
	
	//=========================================================================================
	// PRODUCT COMMENT
	/**
	 * @category PRODUCT COMMENT
	*/
	
	@PostMapping("/comment")
	public ResponseEntity<ProductComment> addComment(@RequestParam("userId") int userId,
			@RequestParam("productId") int productId,
			@RequestParam(name = "parentCommentId", defaultValue = "0") int parentCommentId,
			@RequestBody ProductComment productComment){
		try {
			Users user = usersService.getById(userId);
			Product product = productService.getById(productId);
			ProductComment comment = productComment;
			if(parentCommentId != 0) {
				ProductComment parent = productCommentService.getById(parentCommentId);
				comment.setParentComment(parent);
			}
			comment.setUser(user);
			comment.setProduct(product);	
			productCommentService.add(productComment);
			return new ResponseEntity<ProductComment>(comment, new HttpHeaders(), HttpStatus.OK);
			
		}catch(Exception ex) {
			return new ResponseEntity<ProductComment>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/comment/{productId}")
	public ResponseEntity<List<ProductComment>> getCommentByProduct(@PathVariable("productId") int productId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="product_id") String sortBy){
		try {
			List<ProductComment> list = productCommentService.getCommentByProduct(productId, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductComment>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductComment>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
}
