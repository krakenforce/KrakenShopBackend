package com.krakenforce.app.dtos;

import java.util.Set;

import com.krakenforce.app.model.HomePageBanner;

public class BannerTypeDtos {
	
	private int id;
	private String name;
	private boolean status;
	private Set<HomePageBanner> homePageBanners;
	
	public BannerTypeDtos() {
		
	}
	
	public BannerTypeDtos(int id, String name, boolean status, Set<HomePageBanner> homePageBanners) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.homePageBanners = homePageBanners;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Set<HomePageBanner> getHomePageBanners() {
		return homePageBanners;
	}
	public void setHomePageBanners(Set<HomePageBanner> homePageBanners) {
		this.homePageBanners = homePageBanners;
	}
	
	
}
