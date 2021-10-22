package com.krakenforce.app.dtos;

import java.sql.Timestamp;

public class UserFeedbackDtos {
	private int id;
	private int userId;
	private Timestamp dateTime;
	private int feedbackTypeId;
	private String feedbackTypeName;
	private String detail;

	public UserFeedbackDtos() {
		
	}
	

	public UserFeedbackDtos(int id, int userId, Timestamp dateTime, int feedbackTypeId, String feedbackTypeName,
			String detail) {
		super();
		this.id = id;
		this.userId = userId;
		this.dateTime = dateTime;
		this.feedbackTypeId = feedbackTypeId;
		this.feedbackTypeName = feedbackTypeName;
		this.detail = detail;
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

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public int getFeedbackTypeId() {
		return feedbackTypeId;
	}

	public void setFeedbackTypeId(int feedbackTypeId) {
		this.feedbackTypeId = feedbackTypeId;
	}

	public String getFeedbackTypeName() {
		return feedbackTypeName;
	}

	public void setFeedbackTypeName(String feedbackTypeName) {
		this.feedbackTypeName = feedbackTypeName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	
}
