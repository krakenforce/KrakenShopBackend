package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Orders;
import com.krakenforce.app.model.ProductGameCode;

public class OrderDetailDtos {
	private int id;
	private Orders order;
	private int orderId;
	private String productName;
	private int productId;
	private int gameCodeId;
	private ProductGameCode productGameCode;
	private String gameCode;
	private int quantity;
	private float total;
	private Timestamp createdAt;
	private boolean status;

	public OrderDetailDtos() {
		
	}

	public OrderDetailDtos(int id, Orders order, ProductGameCode productGameCode, int quantity, float total,
			Timestamp createdAt, boolean status) {
		super();
		this.id = id;
		this.order = order;
		this.productGameCode = productGameCode;
		this.quantity = quantity;
		this.total = total;
		this.createdAt = createdAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public ProductGameCode getProductGameCode() {
		return productGameCode;
	}

	public void setProductGameCode(ProductGameCode productGameCode) {
		this.productGameCode = productGameCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public int getGameCodeId() {
		return gameCodeId;
	}

	public void setGameCodeId(int gameCodeId) {
		this.gameCodeId = gameCodeId;
	}
	
	
	
	
	
}
