package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.HomePageBanner;
import com.krakenforce.app.repository.HomePageBannerRepository;

@Service
@Transactional
public class HomePageBannerService {

	@Autowired
	private HomePageBannerRepository homePageBannerRepository;
	
	public HomePageBanner add(HomePageBanner homePageBanner) {
		return homePageBannerRepository.save(homePageBanner);
	}
	
	public void delete(int bannerId) {
		homePageBannerRepository.deleteById(bannerId);
	}
	
	public HomePageBanner getById(int bannerId) {
		return homePageBannerRepository.findById(bannerId).orElse(null);
	}
}
