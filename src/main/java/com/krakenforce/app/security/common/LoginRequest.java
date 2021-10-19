package com.krakenforce.app.security.common;

public class LoginRequest {
	private String username;
	private String password;
	
	/**
	 * Create an empty LoginRequest object
	 */
	public LoginRequest() {
		super();
	}
	

	/**
	 * Create an full attribute LoginRequest object
	 * @param username
	 * @param password
	 */
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
}
