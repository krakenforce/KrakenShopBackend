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

import com.krakenforce.app.dtos.TransactionsDtos;
import com.krakenforce.app.model.Transactions;
import com.krakenforce.app.repository.TransactionsRepository;

@Service
@Transactional
public class TransactionsService {

	@Autowired
	private TransactionsRepository transactionsRepository;
	
	public Transactions add(Transactions transactions) {
		return transactionsRepository.save(transactions);
	}
	
	public void delete(int transactionId) {
		transactionsRepository.deleteById(transactionId);
	}
	
	public Transactions getById(int transactionId) {
		return transactionsRepository.findById(transactionId).orElse(null);
	}
	
	/**
	 * use to get all transaction
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Transaction>
	 */
	public Map<String,Object> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Transactions> list = pageResult.getContent();
			List<TransactionsDtos> dtoList = convertListToDtosList(list);
			
			response.put("transactions", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get transaction by keyword
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Transactions>
	 */
	public Map<String, Object> getByKeyword(String keyword, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findByKeyword(keyword, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Transactions> list = pageResult.getContent();
			List<TransactionsDtos> dtoList = convertListToDtosList(list);
			
			response.put("transactions", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get transaction by time
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Transaction>
	 */
	public Map<String, Object> getByTime(Timestamp startTime, Timestamp endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Transactions> list = pageResult.getContent();
			List<TransactionsDtos> dtoList = convertListToDtosList(list);
			
			response.put("transactions", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<Transactions> getByPaymentId(int paymentId){
		return transactionsRepository.findByPaymentId(paymentId);
	}
	
	public List<TransactionsDtos> convertListToDtosList(List<Transactions> list){
		List<TransactionsDtos> dtosList = new ArrayList<TransactionsDtos>();
		for(Transactions item : list) {
			TransactionsDtos dtos = new TransactionsDtos();
			dtos.setId(item.getId());
			dtos.setComfirms(item.isComfirms());
			dtos.setCreatedAt(item.getCreatedAt());
			dtos.setDescription(item.getDescription());
			dtos.setProvider_fee(item.getProvider_fee());
			dtos.setTotal(item.getTotal());
			dtos.setStatus(item.isStatus());
			dtos.setPaymentId(item.getPayment().getId());
			
			dtosList.add(dtos);
		}
		return dtosList;
	}
}
