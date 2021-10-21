package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.Support;

public class SupportCategoryDtos {
	private int id;
	private String content;
	private boolean status;
	private Set<Support> supports;

	public SupportCategoryDtos() {
		
	}

	public SupportCategoryDtos(int id, String content, boolean status, Set<Support> supports) {
		super();
		this.id = id;
		this.content = content;
		this.status = status;
		this.supports = supports;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Support> getSupports() {
		return supports;
	}

	public void setSupports(Set<Support> supports) {
		this.supports = supports;
	}
	
	
}
