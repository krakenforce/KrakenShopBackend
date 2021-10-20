package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.Users;

public class UserVipClassDtos {
	private int id;
	private String className;
	private int discountPercentage;
	private boolean status;
	private Set<Users> usersSet;

	public UserVipClassDtos() {
		
	}

	public UserVipClassDtos(int id, String className, int discountPercentage, boolean status, Set<Users> usersSet) {
		super();
		this.id = id;
		this.className = className;
		this.discountPercentage = discountPercentage;
		this.status = status;
		this.usersSet = usersSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Users> getUsersSet() {
		return usersSet;
	}

	public void setUsersSet(Set<Users> usersSet) {
		this.usersSet = usersSet;
	}
	
	
}
