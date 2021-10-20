package com.krakenforce.app.dtos;

import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ShoppingCart;

public class CartItemDtos {
	private int id;
	private ShoppingCart shoppingCart;
	private Product product;
	private int quantity;
	private float subTotal;

	public CartItemDtos() {
		
	}

	public CartItemDtos(int id, ShoppingCart shoppingCart, Product product, int quantity, float subTotal) {
		super();
		this.id = id;
		this.shoppingCart = shoppingCart;
		this.product = product;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
