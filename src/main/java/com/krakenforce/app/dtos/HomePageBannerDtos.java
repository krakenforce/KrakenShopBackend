package com.krakenforce.app.dtos;

import com.krakenforce.app.model.BannerType;

public class HomePageBannerDtos {
	private int id;
	private String imageUrl;
	private String apiQueryUrl;
	private boolean status;
	private BannerType bannerType;

	public HomePageBannerDtos() {
		
	}

	public HomePageBannerDtos(int id, String imageUrl, String apiQueryUrl, boolean status, BannerType bannerType) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.apiQueryUrl = apiQueryUrl;
		this.status = status;
		this.bannerType = bannerType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getApiQueryUrl() {
		return apiQueryUrl;
	}

	public void setApiQueryUrl(String apiQueryUrl) {
		this.apiQueryUrl = apiQueryUrl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BannerType getBannerType() {
		return bannerType;
	}

	public void setBannerType(BannerType bannerType) {
		this.bannerType = bannerType;
	}
	
	
}
