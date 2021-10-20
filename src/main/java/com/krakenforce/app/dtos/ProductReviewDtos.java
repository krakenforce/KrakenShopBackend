package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.Users;

public class ProductReviewDtos {
	private int id;
	private Users user;
	private Product product;
	private int starRating;
	private String content;
	private boolean purchaseStatus;
	private Timestamp createdAt;

	public ProductReviewDtos() {
		
	}

	public ProductReviewDtos(int id, Users user, Product product, int starRating, String content,
			boolean purchaseStatus, Timestamp createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.starRating = starRating;
		this.content = content;
		this.purchaseStatus = purchaseStatus;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(boolean purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
