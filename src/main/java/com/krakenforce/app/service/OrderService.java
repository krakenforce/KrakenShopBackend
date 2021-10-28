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

import com.krakenforce.app.model.Orders;
import com.krakenforce.app.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Orders add(Orders order) {
		return orderRepository.save(order);
	}
	
	public void delete(int orderId) {
		orderRepository.deleteById(orderId);
	}
	
	public Orders getById(int orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}
	
	/**
	 * use to get all order with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Order>
	 */
	public List<Orders> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Orders> pageResult = orderRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Orders>();
		}
	}
	
	/**
	 * use to get order list by time 
	 * @param startTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Orders>
	 */
	public List<Orders> getByTime(Instant startTime, Instant endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Orders> pageResult = orderRepository.findOrderByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Orders>();
		}
	}
	
	
}
