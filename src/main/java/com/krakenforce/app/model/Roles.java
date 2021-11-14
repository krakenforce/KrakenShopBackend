package com.krakenforce.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.krakenforce.app.enums.ERole;

@Entity
@Table(name = "roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int role_id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
	private ERole name;
	

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	
	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	
	
}
