package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.FeedbackType;

@Repository
public interface FeedbackTypeRepository extends JpaRepository<FeedbackType, Integer>,
PagingAndSortingRepository<FeedbackType, Integer>{

	@Query(value = "SELECT * FROM feedback_type WHERE name = ?1", nativeQuery = true)
	public FeedbackType findByName(String name);
}
