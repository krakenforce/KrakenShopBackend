package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ProductComment;
import com.krakenforce.app.model.Users;

public class ProductCommentDtos {
	private int id;
	private Users user;
	private int userId;
	private String username;
	private String avatarImageUrl;
	private Product product;
	private int productId;
	private String productName;
	private Timestamp commentTime;
	private String content;
	private boolean status;
	private ProductComment parentComment;

	public ProductCommentDtos() {
		
	}

	public ProductCommentDtos(int id, Users user, Product product, Timestamp commentTime, String content,
			boolean status, ProductComment parentComment) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.commentTime = commentTime;
		this.content = content;
		this.status = status;
		this.parentComment = parentComment;
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

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ProductComment getParentComment() {
		return parentComment;
	}

	public void setParentComment(ProductComment parentComment) {
		this.parentComment = parentComment;
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
