package com.krakenforce.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.Product;
import com.krakenforce.app.repository.ProductRepository;


@Service
@Transactional
public class ProductService {

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
	
	/**
	 * use to get product with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Product>
	 */
	public List<Product> getAllProduct(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
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
	public List<Product> seachProductByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByName(keyword, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
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
	public List<Product> seachProductByServicePack(int servicePackId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByProductServicePack(servicePackId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
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
	public List<Product> seachProductByTag(int tagId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> pageResult = productRepository.getProductByTag(tagId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Product>();
		}
	}
	
	
	
	
	
}
