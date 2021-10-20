package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.Set;

import com.krakenforce.app.model.CartItem;
import com.krakenforce.app.model.Users;

public class ShoppingCartDtos {
	private int id;
	private Users user;
	private float total;
	private Timestamp createdAt;
	private boolean status;
	private Set<CartItem> cartItems;

	public ShoppingCartDtos() {
		
	}

	public ShoppingCartDtos(int id, Users user, float total, Timestamp createdAt, boolean status,
			Set<CartItem> cartItems) {
		super();
		this.id = id;
		this.user = user;
		this.total = total;
		this.createdAt = createdAt;
		this.status = status;
		this.cartItems = cartItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
}
