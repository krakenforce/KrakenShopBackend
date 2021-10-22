package com.krakenforce.app.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.UserFeedback;


@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer>,
PagingAndSortingRepository<UserFeedback, Integer>{
	
	@Query(value = "SELECT * FROM user_feedback WHERE user_id = ?1 ", nativeQuery = true)
	public Page<UserFeedback> findFeedbackByUser(int userId, Pageable pageable);
	
	@Query(value = "SELECT * FROM user_feedback WHERE date_time BETWEEN ?1 AND ?2 ", nativeQuery = true)
	public Page<UserFeedback> findByTime(Instant time1,Instant time2, Pageable pageable);

}
