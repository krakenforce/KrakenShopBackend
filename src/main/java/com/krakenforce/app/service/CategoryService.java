package com.krakenforce.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.Category;
import com.krakenforce.app.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category add(Category category) {
		return categoryRepository.save(category);
	}
	
	public void delete(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
	public Category update(Category updateCategory) {
		Category category = categoryRepository.getById(updateCategory.getCategoryId());
		category = updateCategory;
		return categoryRepository.save(category);
	}
	
	public Category getById(int categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}
	
	
	/**
	 * use to get all Category with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Category>
	 */
	public List<Category> getAllCategory(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pageResult = categoryRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Category>();
		}
	}
	
	/**
	 * use to get all Category by name with pagination
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Category>
	 */
	public List<Category> getAllCategoryByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pageResult = categoryRepository.findCategoryByName(keyword, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Category>();
		}
	}
}
