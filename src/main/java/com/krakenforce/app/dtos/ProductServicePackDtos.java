package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.Product;

public class ProductServicePackDtos {
	private int id;
	private String name;
	private boolean status;
	private Set<Product> products;

	public ProductServicePackDtos() {
		
	}

	public ProductServicePackDtos(int id, String name, boolean status, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
