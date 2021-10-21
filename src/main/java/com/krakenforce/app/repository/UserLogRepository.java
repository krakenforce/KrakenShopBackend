package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.UserLog;


@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Integer>,
PagingAndSortingRepository<UserLog, Integer>{

}
