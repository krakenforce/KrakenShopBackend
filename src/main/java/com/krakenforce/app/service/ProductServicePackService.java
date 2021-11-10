package com.krakenforce.app.service;

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

import com.krakenforce.app.dtos.ProductServicePackDtos;
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
	
	public List<ProductServicePackDtos> getAll(){
		List<ProductServicePack> list = productServicePackRepository.findAll();
		return convertListToDtosList(list);
	}
	
	/**
	 * use to get ProductServicePack with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductServicePack>
	 */
	public Map<String, Object> getAllProductServicePacks(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductServicePack> pageResult = productServicePackRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductServicePack> productServicePacks = pageResult.getContent();
			List<ProductServicePackDtos> dtosList = convertListToDtosList(productServicePacks);		
			response.put("productServicePacks", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
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
	public Map<String, Object> getProductServicePacksByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductServicePack> pageResult = productServicePackRepository.findByName(keyword, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductServicePack> productServicePacks = pageResult.getContent();
			List<ProductServicePackDtos> dtosList = convertListToDtosList(productServicePacks);		
			response.put("productServicePacks", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<ProductServicePackDtos> convertListToDtosList(List<ProductServicePack> productServicePacks){
		List<ProductServicePackDtos> dtosList = new ArrayList<ProductServicePackDtos>();
		for(ProductServicePack item : productServicePacks) {
			ProductServicePackDtos dtos = new ProductServicePackDtos();
			dtos.setId(item.getId());
			dtos.setName(item.getName());
			dtos.setStatus(item.isStatus());
			
			dtosList.add(dtos);
		}
		return dtosList;
	}
}
