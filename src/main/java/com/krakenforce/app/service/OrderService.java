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

import com.krakenforce.app.dtos.OrdersDtos;
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
	public Map<String, Object> getAll(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Orders> pageResult = orderRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Orders> list = pageResult.getContent();
			List<OrdersDtos> dtoList = convertListToDtosList(list);
			
			response.put("orders", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
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
	public Map<String, Object> getByTime(Timestamp startTime, Timestamp endTime, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Orders> pageResult = orderRepository.findOrderByTime(startTime, endTime, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Orders> list = pageResult.getContent();
			List<OrdersDtos> dtoList = convertListToDtosList(list);
			
			response.put("orders", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	List<OrdersDtos> convertListToDtosList(List<Orders> orderList){
		List<OrdersDtos> dtosList = new ArrayList<OrdersDtos>();
		for(Orders item : orderList) {
			OrdersDtos dtos = new OrdersDtos();
			dtos.setId(item.getId());
			dtos.setOrderDateTime(item.getOrderDateTime());
			dtos.setQuantity(item.getQuantity());
			dtos.setStatus(item.isStatus());
			dtos.setTotal(item.getTotal());
			dtos.setWalletId(item.getWallet().getId());
			dtos.setUserId(item.getWallet().getUser().getUserId());
			dtos.setUsername(item.getWallet().getUser().getUsername());
			dtosList.add(dtos);
		}
		return dtosList;
	}
	
}
