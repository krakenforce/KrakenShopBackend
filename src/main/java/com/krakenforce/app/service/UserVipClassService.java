package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krakenforce.app.repository.UserVipClassRepository;

@Service
public class UserVipClassService {
	
	@Autowired
	UserVipClassRepository userVipClassRepository;
	
	
}
