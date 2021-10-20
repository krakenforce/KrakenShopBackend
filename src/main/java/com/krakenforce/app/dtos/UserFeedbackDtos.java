package com.krakenforce.app.dtos;

import java.sql.Timestamp;

import com.krakenforce.app.model.FeedbackType;
import com.krakenforce.app.model.Users;

public class UserFeedbackDtos {
	private int id;
	private Users user;
	private Timestamp dateTime;
	private FeedbackType feedbackType;
	private String detail;

	public UserFeedbackDtos() {
		
	}

	public UserFeedbackDtos(int id, Users user, Timestamp dateTime, FeedbackType feedbackType, String detail) {
		super();
		this.id = id;
		this.user = user;
		this.dateTime = dateTime;
		this.feedbackType = feedbackType;
		this.detail = detail;
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

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
