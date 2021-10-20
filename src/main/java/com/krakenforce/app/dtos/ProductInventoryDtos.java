package com.krakenforce.app.dtos;

import java.sql.Timestamp;
import java.util.Set;

import com.krakenforce.app.model.Product;

public class ProductInventoryDtos {
	private int id;
	private int quantity;
	private float importPrice;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private Set<Product> products;

	public ProductInventoryDtos() {
		
	}

	public ProductInventoryDtos(int id, int quantity, float importPrice, Timestamp createdAt, Timestamp modifiedAt,
			Set<Product> products) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.importPrice = importPrice;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
