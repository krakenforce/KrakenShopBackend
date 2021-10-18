package com.krakenforce.app.payload.response;

import java.util.List;

import com.krakenforce.app.model.Roles;

public class JwtResponse {
	private String token;
	private String tokenType = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<Roles> roles;
	
	
	public JwtResponse(String accessToken, String type, Long id, String username, String email, List<Roles> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getTokenType() {
		return tokenType;
	}


	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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


	public List<Roles> getRoles() {
		return roles;
	}


	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	
	
	
}
