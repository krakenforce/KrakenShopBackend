package com.krakenforce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public OrderDetail add(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}
	
	public void delete(int orderDetailId) {
		orderDetailRepository.findById(orderDetailId).orElse(null);
	}
	
	public List<OrderDetail> getAll(){
		return orderDetailRepository.findAll();
	}
	
	public List<OrderDetail> getByOrderId(int orderId){
		return orderDetailRepository.findByOrderId(orderId);
	}
}
