package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.Users;

public class UserLogDtos {
	private int id;
	private int userId;
	private Timestamp createdAt;
	private String eventDetail;

	public UserLogDtos() {
		
	}

	public UserLogDtos(int id, int userId, Timestamp createdAt, String eventDetail) {
		super();
		this.id = id;
		this.userId = userId;
		this.createdAt = createdAt;
		this.eventDetail = eventDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
