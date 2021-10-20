package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.UserFeedback;

public class FeedbackTypeDtos {
	private int id;
	private String name;
	private Set<UserFeedback> userFeedbacks;

	public FeedbackTypeDtos() {
		
	}

	public FeedbackTypeDtos(int id, String name, Set<UserFeedback> userFeedbacks) {
		super();
		this.id = id;
		this.name = name;
		this.userFeedbacks = userFeedbacks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserFeedback> getUserFeedbacks() {
		return userFeedbacks;
	}

	public void setUserFeedbacks(Set<UserFeedback> userFeedbacks) {
		this.userFeedbacks = userFeedbacks;
	}
	
	
}
