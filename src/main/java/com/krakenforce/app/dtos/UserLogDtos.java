package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Users;

public class UserLogDtos {
	private int id;
	private Users user;
	private Timestamp createdAt;
	private String eventDetail;

	public UserLogDtos() {
		
	}

	public UserLogDtos(int id, Users user, Timestamp createdAt, String eventDetail) {
		super();
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
		this.eventDetail = eventDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}
	
	
}
