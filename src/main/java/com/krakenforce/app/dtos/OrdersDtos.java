package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.Set;

import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.model.Wallet;

public class OrdersDtos {
	private int id;
	private Wallet wallet;
	private Timestamp orderDateTime;
	private int quantity;
	private float total;
	private boolean status;
	private Set<OrderDetail> orderDetails;

	public OrdersDtos() {
		
	}

	public OrdersDtos(int id, Wallet wallet, Timestamp orderDateTime, int quantity, float total, boolean status,
			Set<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.wallet = wallet;
		this.orderDateTime = orderDateTime;
		this.quantity = quantity;
		this.total = total;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Timestamp getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Timestamp orderDateTime) {
		this.orderDateTime = orderDateTime;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
