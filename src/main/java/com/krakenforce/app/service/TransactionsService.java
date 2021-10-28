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
	public List<Transactions> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Transactions>();
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
	public List<Transactions> getByKeyword(String keyword, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findByKeyword(keyword, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Transactions>();
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
	public List<Transactions> getByTime(Instant startTime, Instant endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transactions> pageResult = transactionsRepository.findByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Transactions>();
		}
	}
	
	public List<Transactions> getByPaymentId(int paymentId){
		return transactionsRepository.findByPaymentId(paymentId);
	}
}
