package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	
}
