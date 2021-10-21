package com.krakenforce.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "home_page_banner")
public class HomePageBanner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "api_query_url")
	private String apiQueryUrl;
	
	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "banner_type_id")
	private BannerType bannerType;

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
