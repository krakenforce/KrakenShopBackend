package com.krakenforce.app.dtos;

import java.sql.Timestamp;

public class SalePromoteDtos {
	private int id;
	private String title;
	private Timestamp startDatetime;
	private Timestamp endDatetime;
	private String contentUrl;
	private boolean status;

	public SalePromoteDtos() {
		
	}

	public SalePromoteDtos(int id, String title, Timestamp startDatetime, Timestamp endDatetime, String contentUrl,
			boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.contentUrl = contentUrl;
		this.status = status;
	}

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
