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

import com.krakenforce.app.dtos.TagDtos;
import com.krakenforce.app.model.Tag;
import com.krakenforce.app.repository.TagRepository;

@Service
@Transactional
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	
	public Tag add(Tag tag) {
		return tagRepository.save(tag);
	}
	
	public void delete(int TagId) {
		tagRepository.deleteById(TagId);
	}
	
	public Tag update(Tag updateTag) {
		Tag Tag = tagRepository.getById(updateTag.getTagId());
		Tag = updateTag;
		return tagRepository.save(Tag);
	}
	
	public Tag getById(int TagId) {
		return tagRepository.findById(TagId).orElse(null);
	}
	
	public List<TagDtos> getAll(){
		List<Tag> list = tagRepository.findAll();
		return convertListToDtosList(list);
	}
	
	
	/**
	 * use to get all Tag with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Tag>
	 */
	public Map<String, Object> getAllTag(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Tag> pageResult = tagRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Tag> tagList = pageResult.getContent();
			List<TagDtos> dtosList = convertListToDtosList(tagList);
			
			response.put("tags", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
			
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	/**
	 * use to get all Tag by name with pagination
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Tag>
	 */
	public Map<String, Object> getAllTagByName(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Tag> pageResult = tagRepository.findTagByName(keyword, paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Tag> tagList = pageResult.getContent();
			List<TagDtos> dtosList = convertListToDtosList(tagList);
			
			response.put("tags", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		}else {
			return new HashMap<String, Object>();
		}
	}
	
	public List<TagDtos> convertListToDtosList(List<Tag> tagList){
		List<TagDtos> dtosList = new ArrayList<TagDtos>();
		for(Tag item : tagList) {
			TagDtos dto = new TagDtos();
			dto.setName(item.getName());
			dto.setStatus(item.isStatus());
			dto.setTagId(item.getTagId());
			dtosList.add(dto);
		}
		return dtosList;
	}
}
