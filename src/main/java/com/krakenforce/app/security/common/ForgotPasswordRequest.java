package com.krakenforce.app.security.common;

public class ForgotPasswordRequest {
	private String email;
	

	public ForgotPasswordRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
