package com.krakenforce.app.security.common;

import java.util.List;

public class JwtResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String refreshToken;
	private int id;
	private String username;
	private String email;
	private int cartId;
	private int walletId;
	private float walletBalance;
	private String avatarImageUrl;
	private String vipClassName;
	private int discountPercentage;
	private List<String> roles;

	public JwtResponse(String accessToken, String refreshToken, int id, String username, String email, int cartId,
			int walletId, float walletBalance, String avatarImageUrl, String vipClassName, int discountPercentage,
			List<String> roles) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.cartId = cartId;
		this.walletId = walletId;
		this.walletBalance = walletBalance;
		this.avatarImageUrl = avatarImageUrl;
		this.vipClassName = vipClassName;
		this.discountPercentage = discountPercentage;
		this.roles = roles;
	}
	
	

	public JwtResponse(String accessToken, String refreshToken, int id, String username, String email,
			int cartId, int walletId, float walletBalance, String avatarImageUrl, List<String> roles) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.cartId = cartId;
		this.walletId = walletId;
		this.walletBalance = walletBalance;
		this.avatarImageUrl = avatarImageUrl;
		this.roles = roles;
	}



	public JwtResponse(String token, int id, String username, String email, List<String> roles) {
		this.accessToken = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public float getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(float walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getAvatarImageUrl() {
		return avatarImageUrl;
	}

	public void setAvatarImageUrl(String avatarImageUrl) {
		this.avatarImageUrl = avatarImageUrl;
	}

	public String getVipClassName() {
		return vipClassName;
	}

	public void setVipClassName(String vipClassName) {
		this.vipClassName = vipClassName;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

}
