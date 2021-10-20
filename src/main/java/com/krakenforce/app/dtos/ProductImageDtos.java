package com.krakenforce.app.dtos;

import com.krakenforce.app.model.Product;

public class ProductImageDtos {
	private int id;
	private int priority;
	private String imageUrl;
	private Product product;

	public ProductImageDtos() {
		
	}

	public ProductImageDtos(int id, int priority, String imageUrl, Product product) {
		super();
		this.id = id;
		this.priority = priority;
		this.imageUrl = imageUrl;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}

