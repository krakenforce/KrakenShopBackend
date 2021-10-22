package com.krakenforce.app.dtos;

import com.krakenforce.app.model.SupportCategory;

public class SupportDtos {
	private int id;
	private String content;
	private String contentLink;
	private SupportCategory supportCategory;
	private int supportCategoryId;
	private boolean status;

	public SupportDtos() {
		
	}

	public SupportDtos(int id, String content, String contentLink, SupportCategory supportCategory, boolean status) {
		super();
		this.id = id;
		this.content = content;
		this.contentLink = contentLink;
		this.supportCategory = supportCategory;
		this.status = status;
	}
	
	

	public SupportDtos(int id, String content, String contentLink, int supportCategoryId,boolean status) {
		super();
		this.id = id;
		this.content = content;
		this.contentLink = contentLink;
		this.supportCategoryId = supportCategoryId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentLink() {
		return contentLink;
	}

	public void setContentLink(String contentLink) {
		this.contentLink = contentLink;
	}

	public SupportCategory getSupportCategory() {
		return supportCategory;
	}

	public void setSupportCategory(SupportCategory supportCategory) {
		this.supportCategory = supportCategory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSupportCategoryId() {
		return supportCategoryId;
	}

	public void setSupportCategoryId(int supportCategoryId) {
		this.supportCategoryId = supportCategoryId;
	}
	
	
	
	
}
