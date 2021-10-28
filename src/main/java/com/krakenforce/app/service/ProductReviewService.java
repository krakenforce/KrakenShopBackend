package com.krakenforce.app.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.ProductReview;
import com.krakenforce.app.repository.ProductReviewRepository;

@Service
@Transactional
public class ProductReviewService {

	@Autowired
	private ProductReviewRepository productReviewRepository;
	
	public ProductReview add(ProductReview productReview) {
		return productReviewRepository.save(productReview);
	}
	
	public void delete(int productReviewId) {
		productReviewRepository.deleteById(productReviewId);
	}
	
	public ProductReview getById(int productReviewId) {
		return productReviewRepository.findById(productReviewId).orElse(null);
	}
	
	public List<ProductReview> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductReview>();
		}
	}
	
	public List<ProductReview> getByUser(int userId, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByUser(userId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductReview>();
		}
	}
	
	public List<ProductReview> getByProduct(int productId, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByProduct(productId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductReview>();
		}
	}
	
	public List<ProductReview> getByTime(Instant startTime, Instant endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductReview>();
		}
	}
	
	public List<ProductReview> getByUserAndTime(int userId, Instant startTime, Instant endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByUserAndTime(userId, startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductReview>();
		}
	}
	
	
	
	
}
