package com.krakenforce.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.dtos.HomePageBannerDtos;
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
	
	public List<HomePageBanner> getBannerByTypeId(int typeId){
		return homePageBannerRepository.findBannerByType(typeId);
	}
	
	public Map<String, Object> getAllBanner(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<HomePageBanner> pageResult = homePageBannerRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<HomePageBanner> productServicePacks = pageResult.getContent();
			List<HomePageBannerDtos> dtosList = convertListToDtosList(productServicePacks);		
			response.put("homepageBanners", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<HomePageBannerDtos> convertListToDtosList(List<HomePageBanner> homePageBanners){
		List<HomePageBannerDtos> dtosList = new ArrayList<HomePageBannerDtos>();
		for( HomePageBanner item: homePageBanners) {
			HomePageBannerDtos dto = new HomePageBannerDtos();
			dto.setId(item.getId());
			dto.setApiQueryUrl(item.getApiQueryUrl());
			dto.setImageUrl(item.getImageUrl());
			dto.setStatus(item.isStatus());
			dtosList.add(dto);
		}
		return dtosList;
	}
}
