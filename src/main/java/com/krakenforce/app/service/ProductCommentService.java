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
	
	public List<ProductComment> getCommentByProduct(int productId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductComment> pageResult = productCommentRepository.findCommentByProduct(productId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductComment>();
		}
	}
}
