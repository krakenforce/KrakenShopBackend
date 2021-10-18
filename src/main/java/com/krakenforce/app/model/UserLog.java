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
@Table(name = "user_log")
public class UserLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "event_detail")
	private String eventDetail;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_type_id")
	private EventType eventType;
}
