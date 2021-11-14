package com.krakenforce.app.dtos;

public class TagDtos {
	private int tagId;
	private String name;
	private boolean status;

	public TagDtos() {
		
	}

	public TagDtos(int tagId, String name, boolean status) {
		super();
		this.tagId = tagId;
		this.name = name;
		this.status = status;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
