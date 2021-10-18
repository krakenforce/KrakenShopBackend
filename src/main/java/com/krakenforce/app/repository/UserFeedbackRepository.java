package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.UserFeedback;


@Repository
public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer>,
PagingAndSortingRepository<UserFeedback, Integer>{

}
