package com.krakenforce.app.dtos;

public class CategoryDtos {
	private int categoryId;
	private String name;
	private boolean status;
	
	public CategoryDtos() {
		
	}
	
	public CategoryDtos(int categoryId, String name, boolean status) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.status = status;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
