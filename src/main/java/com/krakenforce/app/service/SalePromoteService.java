package com.krakenforce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<SalePromote> getByKeyword(String keyword){
		return salePromoteRepository.findByKeyword(keyword);
	}
}
