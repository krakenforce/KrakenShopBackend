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

import com.krakenforce.app.dtos.UserVipClassDtos;
import com.krakenforce.app.model.UserVipClass;
import com.krakenforce.app.repository.UserVipClassRepository;

@Service
@Transactional
public class UserVipClassService {
	
	@Autowired
	private UserVipClassRepository userVipClassRepository;
	
	public UserVipClass getById(int vipClassId) {
		return userVipClassRepository.findById(vipClassId).orElse(null);
	}
	
	public Map<String, Object> getAll(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserVipClass> pageResult = userVipClassRepository.findAll(paging);
		if(pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<UserVipClass> userVipClasses = pageResult.getContent();
			List<UserVipClassDtos> dtoList = convertListToDtos(userVipClasses);
			
			response.put("userVipClasses", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
			
		}else {
			return new HashMap<String, Object>();
		}
		
	}	
	
	
	public List<UserVipClassDtos> convertListToDtos(List<UserVipClass> userVipClasses){
		List<UserVipClassDtos> dtoList = new ArrayList<UserVipClassDtos>();
		for(UserVipClass item : userVipClasses) {
			UserVipClassDtos dto = new UserVipClassDtos();
			dto.setId(item.getId());
			dto.setClassName(item.getClassName());
			dto.setDiscountPercentage(item.getDiscountPercentage());
			dto.setStatus(item.isStatus());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	
	
}
