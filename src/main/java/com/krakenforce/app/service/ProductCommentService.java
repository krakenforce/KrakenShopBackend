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

import com.krakenforce.app.dtos.ProductCommentDtos;
import com.krakenforce.app.model.ProductComment;
import com.krakenforce.app.repository.ProductCommentRepository;

@Service
@Transactional
public class ProductCommentService {

	@Autowired
	private ProductCommentRepository productCommentRepository;
	
	public ProductComment add(ProductComment productComment) {
		return productCommentRepository.save(productComment);
	}
	
	public void delete(int productCommentId) {
		productCommentRepository.deleteById(productCommentId);
	}
	
	public ProductComment getById(int productCommentId) {
		return productCommentRepository.getById(productCommentId);
	}
	
	
	/**
	 * use to get all product comment
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductComment>
	 */
	public Map<String, Object> getAllComment (Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductComment> list = pageResult.getContent();
			List<ProductCommentDtos> dtosList = convertListToDtosList(list);
			
			response.put("comments", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get comment on product with pagination
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductComment>
	 */
	public Map<String, Object> getCommentByProduct(int productId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByProduct(productId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductComment> list = pageResult.getContent();
			List<ProductCommentDtos> dtosList = convertListToDtosList(list);
			
			response.put("comments", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get comment on product by user with pagination
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductComment>
	 */
	public Map<String, Object> getCommentByUser(int userId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByUser(userId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductComment> list = pageResult.getContent();
			List<ProductCommentDtos> dtosList = convertListToDtosList(list);
			
			response.put("comments", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get commnet by time
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Comment>
	 */
	public Map<String, Object> getCommentByTime(Timestamp startTime,Timestamp endTime, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductComment> list = pageResult.getContent();
			List<ProductCommentDtos> dtosList = convertListToDtosList(list);
			
			response.put("comments", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get comment by user and time with pagination
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductComment>
	 */
	public List<ProductComment> getCommentByUserAndTime(int userId, Instant startTime, Instant endTime, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByTimeAndUser(userId, startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
		}
	}
	
	public List<ProductCommentDtos> convertListToDtosList(List<ProductComment> list){
		List<ProductCommentDtos> dtosList = new ArrayList<ProductCommentDtos>();
		for(ProductComment item : list) {
			ProductCommentDtos dtos = new ProductCommentDtos();
			dtos.setId(item.getId());
			dtos.setAvatarImageUrl(item.getUser().getAvatarImageUrl());
			dtos.setUserId(item.getUser().getUserId());
			dtos.setUsername(item.getUser().getUsername());
			dtos.setCommentTime(item.getCommentTime());
			dtos.setContent(item.getContent());
			dtos.setProductId(item.getProduct().getProductId());
			dtos.setProductName(item.getProduct().getName());
			dtos.setStatus(item.isStatus());
			
			dtosList.add(dtos);
		}
		return dtosList;
	}
	
	
}
