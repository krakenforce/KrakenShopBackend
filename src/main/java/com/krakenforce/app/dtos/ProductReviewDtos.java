package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.Users;

public class ProductReviewDtos {
	private int id;
	private Users user;
	private String avatarImageUrl;
	private Product product;
	private int productId;
	private String productName;
	private int userId;
	private String username;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public void setAvatarImageUrl(String avatarImageUrl) {
		this.avatarImageUrl = avatarImageUrl;
	}
	
	
	
	
}
