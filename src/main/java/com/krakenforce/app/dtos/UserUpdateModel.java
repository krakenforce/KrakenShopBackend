package com.krakenforce.app.dtos;

import com.krakenforce.app.enums.EGender;

public class UserUpdateModel {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String identityNumber;
	private EGender gender;
	private boolean marriage;
	private String address;
	private String job;
	
	
	
	
	public UserUpdateModel() {
		
	}
	public UserUpdateModel(int userId, String firstName, String lastName, String email, String phone,
			String identityNumber, EGender gender, boolean marriage, String address, String job) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.identityNumber = identityNumber;
		this.gender = gender;
		this.marriage = marriage;
		this.address = address;
		this.job = job;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public EGender getGender() {
		return gender;
	}
	public void setGender(EGender gender) {
		this.gender = gender;
	}
	public boolean isMarriage() {
		return marriage;
	}
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	
}
