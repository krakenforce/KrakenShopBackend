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

import com.krakenforce.app.dtos.ProductGameCodeDtos;
import com.krakenforce.app.model.ProductGameCode;
import com.krakenforce.app.repository.ProductGameCodeRepository;

@Service
@Transactional
public class ProductGameCodeService {

	@Autowired
	private ProductGameCodeRepository productGameCodeRepository;
	
	public ProductGameCode add(ProductGameCode productGameCode) {
		return productGameCodeRepository.save(productGameCode);
	}
	
	public void delete(int gameCodeId) {
		productGameCodeRepository.deleteById(gameCodeId);
	}
	
	public ProductGameCode getById(int gameCodeId) {
		return productGameCodeRepository.findById(gameCodeId).orElse(null);
	}
	
	public List<ProductGameCode> getProductGameCodeByIdAndStatus(int productId, boolean status){
		List<ProductGameCode> list = productGameCodeRepository.findGameCodeByProductIdAndStatus(productId, status);
		//List<ProductGameCodeDtos> dtosList = convertListToDtosList(list);
		return list;
	}
	
	/**
	 * use to get gamecode by product
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductGameCode>
	 */
	public Map<String, Object> getGameCodeByProduct(int productId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductGameCode> pageResult = productGameCodeRepository.findGameCodeByProduct(productId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductGameCode> list = pageResult.getContent();
			List<ProductGameCodeDtos> dtosList = convertListToDtosList(list);
			
			response.put("productGameCodes", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getGameCodeByWallet(int walletId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductGameCode> pageResult = productGameCodeRepository.findGameCodeByWalletId(walletId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<ProductGameCode> list = pageResult.getContent();
			List<ProductGameCodeDtos> dtosList = convertListToDtosList(list);
			
			response.put("productGameCodes", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get game code by product and status
	 * @param productId
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<ProductGameCode>
	 */
	public List<ProductGameCode> getGameCodeByProductAndStatus(int productId,boolean status, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductGameCode> pageResult = productGameCodeRepository.findGameCodeByProductAndStatus(productId, status, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductGameCode>();
		}
	}
	
	
	
	public long getCodeAmountByProduct(int productId) {
		return productGameCodeRepository.countGameCodeByProduct(productId);
	}
	
	public long getCodeAmountByProductAndStatus(int productId, boolean status) {
		return productGameCodeRepository.countGameCodeByProductAndStatus(productId, status);
	}
	
	public List<ProductGameCodeDtos> convertListToDtosList(List<ProductGameCode> list){
		List<ProductGameCodeDtos> dtosList = new ArrayList<ProductGameCodeDtos>();
		for(ProductGameCode item : list) {
			ProductGameCodeDtos dtos = new ProductGameCodeDtos();
			dtos.setId(item.getId());
			dtos.setCode(item.getCode());
			dtos.setStatus(item.isStatus());
			dtos.setProductId(item.getProduct().getProductId());
			dtos.setProductName(item.getProduct().getName());
			dtos.setCreatedAt(item.getCreatedAt());
			
			dtosList.add(dtos);
		}
		return dtosList;
	}
	
}
