package com.krakenforce.app.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.UserLog;


@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Integer>,
PagingAndSortingRepository<UserLog, Integer>{
	
	@Query(value = "SELECT * FROM user_log WHERE user_id = ?1", nativeQuery = true)
	public Page<UserLog> findByUser(int userId, Pageable pageable);
	
	@Query(value = "SELECT * FROM user_log WHERE created_at BETWEEN ?1 AND ?2 ", nativeQuery = true)
	public Page<UserLog> findByTime(Instant time1,Instant time2, Pageable pageable);
	
	@Query(value = "SELECT * FROM user_log WHERE user_id = ?1 && created_at = ?2", nativeQuery = true)
	public Page<UserLog> findByUserAndTime(int userId, Instant createdAt, Pageable pageable);
	
	@Query(value = "SELECT * FROM user_log WHERE event_detail LIKE %?1%", nativeQuery = true)
	public Page<UserLog> findByKeyword(String keyword, Pageable pageable);
	
	
}
