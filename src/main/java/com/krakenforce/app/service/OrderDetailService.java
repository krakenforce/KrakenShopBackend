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

import com.krakenforce.app.dtos.OrderDetailDtos;
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
	
	public Map<String, Object> getByOrderId(int orderId, int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<OrderDetail> pageResult = orderDetailRepository.findByOrderId(orderId, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<OrderDetail> list = pageResult.getContent();
			List<OrderDetailDtos> dtoList = convertListToDtosList(list);
			
			response.put("orderDetails", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<?> getOrderDetailByWalletId(int walletId){
		return orderDetailRepository.findOrderDetailByWalletId(walletId);
	}
	
	
	public List<OrderDetailDtos> convertListToDtosList(List<OrderDetail> list){
		List<OrderDetailDtos> dtosList = new ArrayList<OrderDetailDtos>();
		for(OrderDetail item : list) {
			OrderDetailDtos dto = new OrderDetailDtos();
			dto.setId(item.getId());
			dto.setCreatedAt(item.getCreatedAt());
			dto.setGameCodeId(item.getProductGameCode().getId());
			dto.setOrderId(item.getOrder().getId());
			dto.setProductId(item.getProductGameCode().getProduct().getProductId());
			dto.setProductName(item.getProductGameCode().getProduct().getName());
			dto.setQuantity(item.getQuantity());
			dto.setTotal(item.getTotal());
			dto.setStatus(item.isStatus());
			dto.setGameCode(item.getProductGameCode().getCode());
			
			dtosList.add(dto);
		}
		
		return dtosList;
	}
	
	
}
