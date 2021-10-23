package com.krakenforce.app.security.common;

import java.util.Set;

import com.krakenforce.app.model.Product;

public class ProductResponse {
	
	public Product product;
	public Set<Integer> categoryIdSet;
	public Set<Integer> tagIdSet;
	
	public ProductResponse() {
		
	}
	public ProductResponse(Product product, Set<Integer> categoryIdSet, Set<Integer> tagIdSet) {
		super();
		this.product = product;
		this.categoryIdSet = categoryIdSet;
		this.tagIdSet = tagIdSet;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Set<Integer> getCategoryIdSet() {
		return categoryIdSet;
	}
	public void setCategoryIdSet(Set<Integer> categoryIdSet) {
		this.categoryIdSet = categoryIdSet;
	}
	public Set<Integer> getTagIdSet() {
		return tagIdSet;
	}
	public void setTagIdSet(Set<Integer> tagIdSet) {
		this.tagIdSet = tagIdSet;
	}
	
	
}
