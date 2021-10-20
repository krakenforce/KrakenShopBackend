package com.krakenforce.app.dtos;

import com.krakenforce.app.model.Product;

public class ProductWarrantyDtos {
	private int id;
	private int warrantyTime;
	private String warrantyPolicy;
	private String indemnity;
	private Product product;

	public ProductWarrantyDtos() {
		
	}

	public ProductWarrantyDtos(int id, int warrantyTime, String warrantyPolicy, String indemnity, Product product) {
		super();
		this.id = id;
		this.warrantyTime = warrantyTime;
		this.warrantyPolicy = warrantyPolicy;
		this.indemnity = indemnity;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWarrantyTime() {
		return warrantyTime;
	}

	public void setWarrantyTime(int warrantyTime) {
		this.warrantyTime = warrantyTime;
	}

	public String getWarrantyPolicy() {
		return warrantyPolicy;
	}

	public void setWarrantyPolicy(String warrantyPolicy) {
		this.warrantyPolicy = warrantyPolicy;
	}

	public String getIndemnity() {
		return indemnity;
	}

	public void setIndemnity(String indemnity) {
		this.indemnity = indemnity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
