package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.dtos.ShoppingCartDtos;
import com.krakenforce.app.model.ShoppingCart;
import com.krakenforce.app.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCart add(ShoppingCart cart) {
		return shoppingCartRepository.save(cart);
	}
	
	public ShoppingCart getById(int shoppingCartId) {
		return shoppingCartRepository.findById(shoppingCartId).orElse(null);
	}
	
	public ShoppingCartDtos getByUser(int userId) {
		ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
		ShoppingCartDtos dtos = new ShoppingCartDtos();
		dtos.setId(cart.getId());
		dtos.setTotal(userId);
		dtos.setQuantity(cart.getQuantity());
		dtos.setTotal(cart.getTotal());
		return dtos;
	}
	
}
