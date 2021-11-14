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

import com.krakenforce.app.dtos.CategoryDtos;
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
	
	public List<CategoryDtos> getAll(){
		List<Category> list = categoryRepository.findAll();
		return convertListToDtosList(list);
	}
	
	
	/**
	 * use to get all Category with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Category>
	 */
	public Map<String, Object> getAllCategory(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pageResult = categoryRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Category> list = pageResult.getContent();
			List<CategoryDtos> dtosList = convertListToDtosList(list);
			
			response.put("categories", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
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
	public Map<String,Object> getAllCategoryByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pageResult = categoryRepository.findCategoryByName(keyword, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Category> list = pageResult.getContent();
			List<CategoryDtos> dtosList = convertListToDtosList(list);
			
			response.put("categories", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<CategoryDtos> convertListToDtosList(List<Category> categoryList){
		List<CategoryDtos> dtosList = new ArrayList<CategoryDtos>();
		for(Category item : categoryList) {
			CategoryDtos dto = new CategoryDtos();
			dto.setCategoryId(item.getCategoryId());
			dto.setName(item.getName());
			dto.setStatus(item.isStatus());
			dtosList.add(dto);
		}
		return dtosList;
	}
}
