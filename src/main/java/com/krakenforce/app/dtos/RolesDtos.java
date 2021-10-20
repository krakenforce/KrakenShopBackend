package com.krakenforce.app.dtos;

import com.krakenforce.app.enums.ERole;

public class RolesDtos {
	private int role_id;
	private ERole name;
	private boolean status;

	public RolesDtos() {
		
	}

	public RolesDtos(int role_id, ERole name, boolean status) {
		super();
		this.role_id = role_id;
		this.name = name;
		this.status = status;
	}

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
