package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.repository.FeedbackTypeRepository;

@Service
@Transactional
public class FeedbackTypeService {

	@Autowired
	private FeedbackTypeRepository feedbackTypeRepository;
}
