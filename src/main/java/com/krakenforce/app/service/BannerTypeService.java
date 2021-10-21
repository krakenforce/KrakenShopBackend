package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.repository.BannerTypeRepository;

@Service
@Transactional
public class BannerTypeService {

	@Autowired
	private BannerTypeRepository bannerTypeRepository;
}
