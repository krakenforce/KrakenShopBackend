package com.krakenforce.app.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.dtos.ProductDtos;
import com.krakenforce.app.dtos.ProductReviewDtos;
import com.krakenforce.app.model.Product;
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

	public Map<String, Object> getAll(int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findAll(paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductReview> list = pageResult.getContent();
			List<ProductReviewDtos> dtosList = convertListToDtosList(list);
			
			response.put("reviews", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		} else {
			return new HashMap<String, Object>();
		}
	}

	public List<ProductReview> getByUser(int userId, int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByUser(userId, paging);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<ProductReview>();
		}
	}

	public List<ProductReview> getByProduct(int productId, int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByProduct(productId, paging);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<ProductReview>();
		}
	}

	public Map<String, Object> getByTime(Timestamp startTime, Timestamp endTime, int pageNo, int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByTime(startTime, endTime, paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductReview> list = pageResult.getContent();
			List<ProductReviewDtos> dtosList = convertListToDtosList(list);
			
			response.put("reviews", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		} else {
			return new HashMap<String, Object>();
		}
	}

	public List<ProductReview> getByUserAndTime(int userId, Instant startTime, Instant endTime, int pageNo,
			int pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductReview> pageResult = productReviewRepository.findByUserAndTime(userId, startTime, endTime, paging);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<ProductReview>();
		}
	}

	public List<ProductReviewDtos> convertListToDtosList(List<ProductReview> list){
		List<ProductReviewDtos> dtosList = new ArrayList<ProductReviewDtos>();
		for(ProductReview item : list) {
			ProductReviewDtos dtos = new ProductReviewDtos();
			dtos.setId(item.getId());
			dtos.setContent(item.getContent());
			dtos.setCreatedAt(item.getCreatedAt());
			dtos.setPurchaseStatus(item.isPurchaseStatus());
			dtos.setStarRating(item.getStarRating());
			dtos.setUserId(item.getUser().getUserId());
			dtos.setUsername(item.getUser().getUsername());
			dtos.setProductId(item.getProduct().getProductId());
			dtos.setProductName(item.getProduct().getName());
			
			dtosList.add(dtos);
		}
		return dtosList;
	}

}
