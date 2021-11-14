package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.Set;

import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.model.Product;

public class ProductGameCodeDtos {
	private int id;
	private String code;
	private boolean status;
	private Product product;
	private int productId;
	private String productName;
	private Set<OrderDetail> orderDetails;
	private Timestamp createdAt;

	public ProductGameCodeDtos(int id, String code, boolean status, Product product, Set<OrderDetail> orderDetails,
			Timestamp createdAt) {
		super();
		this.id = id;
		this.code = code;
		this.status = status;
		this.product = product;
		this.orderDetails = orderDetails;
		this.createdAt = createdAt;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
}
