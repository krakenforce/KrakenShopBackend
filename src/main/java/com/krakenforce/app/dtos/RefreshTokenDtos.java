package com.krakenforce.app.dtos;

import java.time.Instant;

import com.krakenforce.app.model.Users;

public class RefreshTokenDtos {
	private long id;
	private Users user;
	private String token;
	private Instant expiryDate;

	public RefreshTokenDtos() {
		
	}

	public RefreshTokenDtos(long id, Users user, String token, Instant expiryDate) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.expiryDate = expiryDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
