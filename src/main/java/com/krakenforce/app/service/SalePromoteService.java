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

import com.krakenforce.app.dtos.SalePromoteDtos;
import com.krakenforce.app.model.SalePromote;
import com.krakenforce.app.repository.SalePromoteRepository;

@Service
@Transactional
public class SalePromoteService {

	@Autowired
	private SalePromoteRepository salePromoteRepository;
	
	public SalePromote add(SalePromote salePromote) {
		return salePromoteRepository.save(salePromote);
	}
	
	public SalePromote getById(int salePromoteId) {
		return salePromoteRepository.findById(salePromoteId).orElse(null);
	}
	
	public List<SalePromote> getAll(){
		return salePromoteRepository.findAll(); 
	}
	
	public Map<String, Object> getAllSalePromote(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<SalePromote> pageResult = salePromoteRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<SalePromote> productServicePacks = pageResult.getContent();
			List<SalePromoteDtos> dtosList = convertListToDtosList(productServicePacks);		
			response.put("salePromotes", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getSalePromoteByKeyword(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<SalePromote> pageResult = salePromoteRepository.findByKeyword(keyword,paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<SalePromote> productServicePacks = pageResult.getContent();
			List<SalePromoteDtos> dtosList = convertListToDtosList(productServicePacks);		
			response.put("salePromotes", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<SalePromoteDtos> convertListToDtosList(List<SalePromote> salePromotes){
		List<SalePromoteDtos> dtosList = new ArrayList<SalePromoteDtos>();
		for(SalePromote item: salePromotes) {
			SalePromoteDtos dto = new SalePromoteDtos();
			dto.setId(item.getId());
			dto.setContentUrl(item.getContentUrl());
			dto.setEndDatetime(item.getEndDatetime());
			dto.setStartDatetime(item.getStartDatetime());
			dto.setTitle(item.getTitle());
			dto.setStatus(item.isStatus());
			dtosList.add(dto);
		}
		return dtosList;
	}
}
