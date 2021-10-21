package com.krakenforce.app.security.common;

public class ResetPasswordRequest {
	
	private String token;
	private String password;
	

	public ResetPasswordRequest(String password, String token) {
		super();
		this.password = password;
		this.token = token;
	}
	
	

	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
