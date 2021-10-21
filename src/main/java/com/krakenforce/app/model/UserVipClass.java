package com.krakenforce.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_vip_class")
public class UserVipClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "discount_percentage")
	private int discountPercentage;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(mappedBy = "userVipClass", fetch = FetchType.LAZY)
	private Set<Users> usersSet;

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
