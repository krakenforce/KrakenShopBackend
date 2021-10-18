package com.krakenforce.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_feedback")
public class UserFeedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@Column(name = "date_time")
	private Timestamp dateTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feedback_type_id")
	private FeedbackType feedbackType;
	
	@Column(name = "detail")
	private String detail;
}
