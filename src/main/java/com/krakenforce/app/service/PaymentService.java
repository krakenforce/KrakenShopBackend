package com.krakenforce.app.service;

import java.sql.Timestamp;
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

import com.krakenforce.app.dtos.PaymentDtos;
import com.krakenforce.app.model.Payments;
import com.krakenforce.app.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payments add(Payments payment) {
		return paymentRepository.save(payment);
	}
	
	public void delete(int paymentId) {
		paymentRepository.deleteById(paymentId);
	}
	
	public Payments getById(int paymentId) {
		return paymentRepository.findById(paymentId).orElse(null);
	}
	
	public Map<String, Object> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Payments> list = pageResult.getContent();
			List<PaymentDtos> dtoList = convertListToDtosList(list);
			
			response.put("payments", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getByProvider(String provider, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findByProvider(provider, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Payments> list = pageResult.getContent();
			List<PaymentDtos> dtoList = convertListToDtosList(list);
			
			response.put("payments", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getByWalletId(int walletId, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findByWalletId(walletId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Payments> list = pageResult.getContent();
			List<PaymentDtos> dtoList = convertListToDtosList(list);
			
			response.put("payments", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getByTime(Timestamp startTime, Timestamp endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Payments> list = pageResult.getContent();
			List<PaymentDtos> dtoList = convertListToDtosList(list);
			
			response.put("payments", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<PaymentDtos> convertListToDtosList(List<Payments> paymentList){
		List<PaymentDtos> dtosList = new ArrayList<PaymentDtos>();
		for(Payments item : paymentList) {
			PaymentDtos dto = new PaymentDtos();
			dto.setId(item.getId());
			//dto.setWallet(item.getWallet());
			dto.setTotalPrice(item.getTotalPrice());
			dto.setCreatedAt(item.getCreatedAt());
			dto.setProvider(item.getProvider());
			dto.setWalletId(item.getWallet().getId());
			dto.setUserId(item.getWallet().getUser().getUserId());
			dtosList.add(dto);
		}
		return dtosList;
	}
	
	
	
}
