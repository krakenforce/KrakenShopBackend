package com.krakenforce.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.dtos.CategoryChild;
import com.krakenforce.app.dtos.ProductDtos;
import com.krakenforce.app.dtos.TagChild;
import com.krakenforce.app.model.Category;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.repository.ProductRepository;


@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductReviewService productReviewService;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public Product update(Product updateProduct) {
		Product product = productRepository.getById(updateProduct.getProductId());
		if(product != null) {
			product = updateProduct;
			return productRepository.save(product);
		}else {
			return null;
		}
	}
	
	public void delete(int productId) {
		productRepository.deleteById(productId);
	}
	
	
	public Product getById(int productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
	public ProductDtos getDtosById(int productId) {
		Product product = productRepository.findById(productId).orElse(null);
		return convertModelToDtos(product);
	}
	
	/**
	 * use to get product with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public Map<String ,Object> getAllProduct(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
		
	}
	
	/**
	 * use to get product below price with pagination
	 * @param price
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public List<Product> getAllProductBelowPrice(float price, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.GetProductBelowPrice(price,paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
		}
		
	}
	
	/**
	 * use to get product by price range with pagination
	 * @param startPrice
	 * @param endPrice
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public List<Product> getAllProductPriceRange(float startPrice, float endPrice, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.GetProductPriceRange(startPrice, endPrice,paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
		}
		
	}
	
	/**
	 * use to get product by name with pagination
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public Map<String,Object> seachProductByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByName(keyword, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get product by product service pack with pagination
	 * @param servicePackId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public Map<String, Object> seachProductByServicePack(int servicePackId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByProductServicePack(servicePackId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get product by tag id
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public Map<String, Object> seachProductByTag(int tagId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByTag(tagId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> seachProductByTagName(String tagName, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByTagName(tagName, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get product by category id
	 * @param categoryId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	public Map<String, Object> seachProductByCategory(int categoryId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByCategory(categoryId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> seachProductByCategoryName(String categoryName, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByCategoryName(categoryName, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Product> productList = pageResult.getContent();
			List<ProductDtos> dtosList = convertListToDtosList(productList);
			
			response.put("products", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get user favorite product list
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public List<Product> seachFavoriteProductByUser(int userId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getFavoriteProductByUser(userId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
		}
	}
	
	public List<ProductDtos> convertListToDtosList(List<Product> products){
		List<ProductDtos> dtosList = new ArrayList<ProductDtos>();
		for(Product item : products) {
			ProductDtos productDtos = new ProductDtos();
			productDtos.setProductId(item.getProductId());
			productDtos.setProductCode(item.getProductCode());
			productDtos.setName(item.getName());
			productDtos.setPrice(item.getPrice());
			productDtos.setSalePrice(item.getSalePrice());
			productDtos.setProductServicePackId(item.getProductServicePack().getId());
			productDtos.setProductServicePackName(item.getProductServicePack().getName());
			productDtos.setOriginalProductLink(item.getOriginalProductLink());
			productDtos.setCategoryChilds(convertCategory(item.getCategories()));
			productDtos.setTagChilds(convertTag(item.getTags()));
			productDtos.setProductDetail(item.getProductDetail());
			productDtos.setProductWarranty(item.getProductWarranty());
			productDtos.setStatus(item.isStatus());
			productDtos.setThumbnailImageUrl(item.getThumbnailImageUrl());
			productDtos.setAvgStar(productReviewService.getAverageStart(item.getProductId()));
			//productDtos.setProductImages(item.getProductImages());
			
			dtosList.add(productDtos);
		}
		return dtosList;
		
	}
	
	public ProductDtos convertModelToDtos(Product item) {
		ProductDtos productDtos = new ProductDtos();
		productDtos.setProductId(item.getProductId());
		productDtos.setProductCode(item.getProductCode());
		productDtos.setName(item.getName());
		productDtos.setPrice(item.getPrice());
		productDtos.setSalePrice(item.getSalePrice());
		productDtos.setProductServicePackId(item.getProductServicePack().getId());
		productDtos.setProductServicePackName(item.getProductServicePack().getName());
		productDtos.setOriginalProductLink(item.getOriginalProductLink());
		productDtos.setCategoryChilds(convertCategory(item.getCategories()));
		productDtos.setTagChilds(convertTag(item.getTags()));
		productDtos.setProductDetail(item.getProductDetail());
		productDtos.setProductWarranty(item.getProductWarranty());
		productDtos.setStatus(item.isStatus());
		productDtos.setThumbnailImageUrl(item.getThumbnailImageUrl());
		productDtos.setAvgStar(productReviewService.getAverageStart(item.getProductId()));
		return productDtos;
	}
	
	public Set<CategoryChild> convertCategory(Set<Category> categories){
		Set<CategoryChild> list = new HashSet<CategoryChild>();
		for(Category item : categories) {
			CategoryChild child = new CategoryChild();
			child.setCategoryId(item.getCategoryId());
			child.setName(item.getName());
			list.add(child);
		}
		return list;
	}
	
	public Set<TagChild> convertTag(Set<Tag> tags){
		Set<TagChild> list = new HashSet<TagChild>();
		for(Tag item : tags) {
			TagChild child = new TagChild();
			child.setTagId(item.getTagId());
			child.setName(item.getName());
			list.add(child);
		}
		return list;
	}
	
	
	
	
}
