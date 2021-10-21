package com.krakenforce.app.dtos;

import com.krakenforce.app.model.Product;

public class ProductDetailDtos {
	private int id;
	private String note;
	private String detail;
	private String systemRequired;
	private Product product;

	public ProductDetailDtos() {
		
	}

	public ProductDetailDtos(int id, String note, String detail, String systemRequired, Product product) {
		super();
		this.id = id;
		this.note = note;
		this.detail = detail;
		this.systemRequired = systemRequired;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSystemRequired() {
		return systemRequired;
	}

	public void setSystemRequired(String systemRequired) {
		this.systemRequired = systemRequired;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
