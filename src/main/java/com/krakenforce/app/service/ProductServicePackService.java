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

import com.krakenforce.app.model.ProductServicePack;
import com.krakenforce.app.repository.ProductServicePackRepository;

@Service
@Transactional
public class ProductServicePackService {

	@Autowired
	private ProductServicePackRepository productServicePackRepository;
	
	public ProductServicePack add(ProductServicePack productServicePack) {
		return productServicePackRepository.save(productServicePack);
	}
	
	public void delete(int productServicePackId) {
		productServicePackRepository.deleteById(productServicePackId);
	}
	
	public ProductServicePack getById(int servicePackId) {
		return productServicePackRepository.findById(servicePackId).orElse(null);
	}
	
	/**
	 * use to get ProductServicePack with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductServicePack>
	 */
	public List<ProductServicePack> getAllProductServicePacks(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductServicePack> pageResult = productServicePackRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductServicePack>();
		}
	}
	
	/**
	 * use to get ProductServicePack with pagination
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductServicePack>
	 */
	public List<ProductServicePack> getProductServicePacksByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductServicePack> pageResult = productServicePackRepository.findByName(keyword, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductServicePack>();
		}
	}
}
