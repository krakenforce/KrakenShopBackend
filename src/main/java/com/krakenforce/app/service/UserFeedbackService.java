package com.krakenforce.app.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.UserFeedback;
import com.krakenforce.app.repository.UserFeedbackRepository;

@Service
@Transactional
public class UserFeedbackService {

	@Autowired
	private UserFeedbackRepository userFeedbackRepository;
	
	@Autowired
	FeedbackTypeService feedbackTypeService;
	
	/**
	 * use to add userFeedback
	 * @param userFeedback
	 * @return UserFeeback
	 */
	public UserFeedback add(UserFeedback userFeedback) {
		return userFeedbackRepository.save(userFeedback);
	}
	
	/**
	 * use to get userFeedback by id
	 * @param feedbackId
	 * @return UserFeeback
	 */
	public UserFeedback getById(int feedbackId) {
		return userFeedbackRepository.findById(feedbackId).orElse(null);
	}
	
	
	/**
	 * use to delete user feedback by id
	 * @param feedbackId
	 */
	public void delete(int feedbackId) {
		userFeedbackRepository.deleteById(feedbackId);
	}
	
	/**
	 * use get all user feedback with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserFeedback>
	 */
	public List<UserFeedback> getAllUserFeedback(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserFeedback> pagedResult = userFeedbackRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserFeedback>();
		}
	}
	
	/**
	 * use get user feedback by userId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserFeedback>
	 */
	public List<UserFeedback> getFeedbackByUser(int userId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserFeedback> pagedResult = userFeedbackRepository.findFeedbackByUser(userId, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserFeedback>();
		}
	}
	
	/**
	 * use to get user feedback by time range
	 * @param time1
	 * @param time2
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserFeedback>
	 */
	public List<UserFeedback> getFeedbackByTime(Instant time1, Instant time2, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserFeedback> pagedResult = userFeedbackRepository.findByTime(time1, time2, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserFeedback>();
		}
	}
	
	
	
}
