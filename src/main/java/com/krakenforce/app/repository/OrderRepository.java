package com.krakenforce.app.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>,
PagingAndSortingRepository<Orders, Integer>{
	
	
	@Query(value = "SELECT * FROM orders WHERE order_datetime BETWEEN ?1 AND ?2", nativeQuery = true)
	Page<Orders> findOrderByTime(Instant startTime, Instant endTime, Pageable pageable);
}
