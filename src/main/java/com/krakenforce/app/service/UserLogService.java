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

import com.krakenforce.app.model.UserLog;
import com.krakenforce.app.repository.UserLogRepository;

@Service
@Transactional
public class UserLogService {

	@Autowired
	private UserLogRepository userLogRepository;
	
	/**
	 * use get all user log with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserLog>
	 */
	public List<UserLog> getAllUserLog(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserLog> pagedResult = userLogRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserLog>();
		}
	}
	
	/**
	 * use to get user log by user id
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserLog>
	 */
	public List<UserLog> getLogByUserId(int userId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserLog> pagedResult = userLogRepository.findByUser(userId, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserLog>();
		}
	}
	
	/**
	 * use to get user log by timestamp
	 * @param createAt
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserLog>
	 */
	public List<UserLog> getLogByTime(Instant time1, Instant time2, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserLog> pagedResult = userLogRepository.findByTime(time1, time2, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserLog>();
		}
	}
	
	/**
	 * use to get user log by user and time
	 * @param userId
	 * @param createAt
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserLog>
	 */
	public List<UserLog> getLogByUserAndTime(int userId,Instant createAt, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserLog> pagedResult = userLogRepository.findByUserAndTime(userId, createAt, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserLog>();
		}
	}
	
	/**
	 * use to find user log by keyword
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<UserLog>
	 */
	public List<UserLog> getLogByKeyword(String keyword, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<UserLog> pagedResult = userLogRepository.findByKeyword(keyword, paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<UserLog>();
		}
	}
	
	
	
	/**
	 * use to save user log
	 * @param userLog
	 * @return userLog
	 */
	public UserLog save(UserLog userLog) {
		return userLogRepository.save(userLog);
	}
	
	/**
	 * use to delete user log
	 * @param userLogId
	 */
	public void delete(int userLogId) {
		userLogRepository.deleteById(userLogId);
	}
	
	
}
