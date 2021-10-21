package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.repository.UserFeedbackRepository;

@Service
@Transactional
public class UserFeedbackService {

	@Autowired
	private UserFeedbackRepository userFeedbackRepository;
}
