package com.krakenforce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.SupportCategory;
import com.krakenforce.app.repository.SupportCategoryRepository;

@Service
@Transactional
public class SupportCategoryService {

	@Autowired
	private SupportCategoryRepository supportCategoryRepository;
	
	public SupportCategory add(SupportCategory supportCategory) {
		return supportCategoryRepository.save(supportCategory);
	}
	
	public SupportCategory update(SupportCategory supportCategory) {
		SupportCategory category = supportCategoryRepository.getById(supportCategory.getId());
		category = supportCategory;
		return supportCategoryRepository.save(category);
	} 
	
	public void delete(int supportCategoryId) {
		supportCategoryRepository.deleteById(supportCategoryId);
	}
	
	public List<SupportCategory> getAllCategory(){
		return supportCategoryRepository.findAll();
	}
	
	public SupportCategory getCategoryById(int supportCategoryId) {
		return supportCategoryRepository.findById(supportCategoryId).orElse(null);
	}
}
