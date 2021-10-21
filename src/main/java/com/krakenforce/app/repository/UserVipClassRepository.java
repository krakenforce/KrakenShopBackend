package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.UserVipClass;

@Repository
public interface UserVipClassRepository extends JpaRepository<UserVipClass, Integer>,
PagingAndSortingRepository<UserVipClass, Integer>{

}
