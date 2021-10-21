package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>,
PagingAndSortingRepository<Orders, Integer>{
	
}
