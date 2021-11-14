package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.krakenforce.app.model.ProductReview;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.security.common.ProductResponse;
import com.krakenforce.app.service.CategoryService;
import com.krakenforce.app.service.FileStorageService;
import com.krakenforce.app.service.ProductCommentService;
import com.krakenforce.app.service.ProductImageService;
import com.krakenforce.app.service.ProductReviewService;
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
	
	@Autowired
	ProductReviewService productReviewService;
	
	//==================================================================================
	// PRODUCT MODULE
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart(value = "productRequest", required = true) ProductResponse productResponse
			,@RequestPart(value = "thumbnailImage", required = false) MultipartFile thumbnailImage,
			@RequestPart(value = "imageList", required = false) List<MultipartFile> imageList){
		try {
			Set<Category> categories = productResponse.getCategories();
			Set<Tag> tags = productResponse.getTags();
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
			ex.printStackTrace();
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
	public ResponseEntity<Map<String, Object>> getAllProduct(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			Map<String, Object> response = productService.getAllProduct(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDtos> getProductById(@PathVariable("productId") int productId){
		try {
			ProductDtos dtos = productService.getDtosById(productId);
			return new ResponseEntity<ProductDtos>(dtos, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductDtos>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
			
				dtosList.add(dtos);
			}
			
			
			return new ResponseEntity<List<ProductDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/name")
	public ResponseEntity<Map<String, Object>> getProductByName(@RequestParam("productName") String productName,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="product_id") String sortBy){
		
		try {
			Map<String, Object> response = productService.seachProductByName(productName,pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search/service_pack")
	public ResponseEntity<Map<String, Object>> getProductByServicePack(@RequestParam("servicePackId") int servicePackId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="product_id") String sortBy){
		
		try {
			Map<String, Object> response = productService.seachProductByServicePack(servicePackId,pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
				
				dtosList.add(dtos);
			}
			
			
			return new ResponseEntity<List<ProductDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/tag/{tagId}")
	public ResponseEntity<Map<String, Object>> getProductByTag(@PathVariable("tagId") String tagId, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			int id = Integer.parseInt(tagId);
			Map<String ,Object> response = productService.seachProductByTag(id,pageNo, pageSize, sortBy);
			
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/tag/search")
	public ResponseEntity<Map<String, Object>> getProductByTagName(@RequestParam("tagName") String tagName, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			Map<String ,Object> response = productService.seachProductByTagName(tagName,pageNo, pageSize, sortBy);
			
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Map<String, Object>> getProductByCategory(@PathVariable("categoryId") String categoryId, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			int id = Integer.parseInt(categoryId);
			Map<String, Object> response = productService.seachProductByCategory(id,pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category/search")
	public ResponseEntity<Map<String, Object>> getProductByCategoryName(@RequestParam("categoryName") String categoryName, 
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="productId") String sortBy){
		try {
			Map<String, Object> response = productService.seachProductByCategoryName(categoryName,pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
			Date date =  new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			comment.setCommentTime(timestamp);
			comment.setUser(user);
			comment.setProduct(product);
			comment.setStatus(true);
			productCommentService.add(productComment);
			return new ResponseEntity<ProductComment>(comment, new HttpHeaders(), HttpStatus.OK);
			
		}catch(Exception ex) {
			return new ResponseEntity<ProductComment>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<MessageResponse> deleteComment(@PathVariable("commentId") int commentId){
		try {
			productCommentService.delete(commentId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<ProductComment> getCommentById(@PathVariable("commentId") int commentId){
		try {
			ProductComment productComment = productCommentService.getById(commentId);
			return new ResponseEntity<ProductComment>(productComment, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductComment>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping("/comment")
	public ResponseEntity<Map<String, Object>> getAllComment(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productCommentService.getAllComment(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/comment/product/{productId}")
	public ResponseEntity<Map<String, Object>> getCommentByProduct(@PathVariable("productId") int productId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productCommentService.getCommentByProduct(productId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/comment/user/{userId}")
	public ResponseEntity<Map<String, Object>> getCommentByUser(@PathVariable("userId") int userId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productCommentService.getCommentByUser(userId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/comment/time")
	public ResponseEntity<Map<String, Object>> getCommentByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			Map<String, Object> list = productCommentService.getCommentByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/comment/user_time")
	public ResponseEntity<List<ProductComment>> getCommentByUserAndTime(@RequestParam("userId") int userId,
			@RequestParam("startTime") Instant startTime,
			@RequestParam("endTime") Instant endTime,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			List<ProductComment> list = productCommentService.getCommentByUserAndTime(userId, startTime, endTime, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductComment>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<ProductComment>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/// PRODUCT REVIEW MODULE
	
	@PostMapping("/review")
	public ResponseEntity<ProductReview> addReview(@RequestParam("userId") int userId,
			@RequestParam("productId") int productId,
			@RequestBody ProductReview productReview){
		try {
			Date date = new Date();
			Timestamp timestamp2 = new Timestamp(date.getTime());
			Users user = usersService.getById(userId);
			Product product = productService.getById(productId);
			productReview.setProduct(product);
			productReview.setPurchaseStatus(true);
			productReview.setCreatedAt(timestamp2);
			productReview.setUser(user);
			
			productReviewService.add(productReview);
			return new ResponseEntity<ProductReview>(productReview, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductReview>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<MessageResponse> deleteReview(@PathVariable("reviewId") int reviewId){
		try {
			productReviewService.delete(reviewId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/review/{reviewId}")
	public ResponseEntity<ProductReview> getReviewById(@PathVariable("reviewId") int reviewId){
		try {
			ProductReview productReview = productReviewService.getById(reviewId);
			return new ResponseEntity<ProductReview>(productReview, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ProductReview>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/review")
	public ResponseEntity<Map<String, Object>> getAllReview(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productReviewService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/review/user/{userId}")
	public ResponseEntity<Map<String, Object>> getReviewByUser(@PathVariable("userId") int userId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productReviewService.getByUser(userId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/review/product/{productId}")
	public ResponseEntity<Map<String, Object>> getReviewByProduct(@PathVariable("productId") int productId,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Map<String, Object> list = productReviewService.getByProduct(productId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/review/time")
	public ResponseEntity<Map<String, Object>> getReviewByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			Map<String, Object> list = productReviewService.getByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/review/user_time")
	public ResponseEntity<List<ProductReview>> getReviewByUserAndTime(@RequestParam("userId") int userId,
			@RequestParam("startTime") Instant startTime,
			@RequestParam("endTime") Instant endTime,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="id") String sortBy){
		try {
			List<ProductReview> list = productReviewService.getByUserAndTime(userId, startTime, endTime, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<ProductReview>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<ProductReview>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/review/star")
	public ResponseEntity<Integer> getAverageStar(@RequestParam("productId") int productId) {
		try {
			int star = productReviewService.getAverageStart(productId);
			return new ResponseEntity<Integer>(star, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
}
