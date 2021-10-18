package com.krakenforce.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_promote")
public class SalePromote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "start_datetime")
	private Timestamp startDatetime;
	
	@Column(name = "end_datetime")
	private Timestamp endDatetime;
	
	@Column(name = "content_url")
	private String contentUrl;
	
	@Column(name = "status")
	private boolean status;

}
