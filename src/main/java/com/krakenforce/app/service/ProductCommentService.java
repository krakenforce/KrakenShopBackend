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
	public List<ProductComment> getAllComment (Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
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
	public List<ProductComment> getCommentByProduct(int productId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByProduct(productId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
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
	public List<ProductComment> getCommentByUser(int userId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByUser(userId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
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
	public List<ProductComment> getCommentByTime(Instant startTime, Instant endTime, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
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
	
	
}
