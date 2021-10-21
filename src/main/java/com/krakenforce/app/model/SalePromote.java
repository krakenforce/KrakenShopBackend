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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Timestamp startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Timestamp getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
