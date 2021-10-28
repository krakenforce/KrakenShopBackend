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
	
	public List<Payments> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Payments>();
		}
	}
	
	public List<Payments> getByProvider(String provider, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findByProvider(provider, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Payments>();
		}
	}
	
	public List<Payments> getByTime(Instant startTime, Instant endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Payments> pageResult = paymentRepository.findByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Payments>();
		}
	}
	
	
	
}
