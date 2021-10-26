package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.CartItem;
import com.krakenforce.app.repository.CartItemRepository;

@Service
@Transactional
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	public CartItem add(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
	public CartItem getById(int itemId) {
		return cartItemRepository.findById(itemId).orElse(null);
	}
	
	public CartItem getByProductAndCart(int cartId, int productId) {
		return cartItemRepository.findByProductAndCart(cartId, productId);
	}
}
