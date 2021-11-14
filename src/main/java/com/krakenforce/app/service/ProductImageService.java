package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.ProductImage;
import com.krakenforce.app.repository.ProductImageRepository;

@Service
@Transactional
public class ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;
	
	public ProductImage add(ProductImage productImage) {
		return productImageRepository.save(productImage);
	}
	
	public void delete(int productImageId) {
		productImageRepository.deleteById(productImageId);
	}
}
