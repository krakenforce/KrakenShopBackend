package com.krakenforce.app.dtos;

public class GameCodeDetail {
	private int productId;
	private int quantity;
	private float totalPrice;
	
	public GameCodeDetail() {
		
	}
	
	public GameCodeDetail(int productId, int quantity, float totalPrice) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
