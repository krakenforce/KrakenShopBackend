package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.model.Product;

public class ProductGameCodeDtos {
	private int id;
	private String code;
	private boolean status;
	private Product product;
	private Set<OrderDetail> orderDetails;

	public ProductGameCodeDtos() {
		
	}

	public ProductGameCodeDtos(int id, String code, boolean status, Product product, Set<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.code = code;
		this.status = status;
		this.product = product;
		this.orderDetails = orderDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
