package com.krakenforce.app.repository;

import java.sql.Timestamp;

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
	Page<Orders> findOrderByTime(Timestamp startTime, Timestamp endTime, Pageable pageable);
	
	@Query(value = "SELECT * FROM orders WHERE wallet_id = ?1", nativeQuery = true)
	Page<Orders> findOrderByUser(int walletId, Pageable pageable);
	
	
	@Query(value = "select round(sum(total), 2) from orders", nativeQuery = true)
	float getTotalRevenue();
	
	@Query(value = "select round(sum(total), 2) from orders WHERE order_datetime BETWEEN ?1 AND ?2", nativeQuery = true)
	float getRevenueByTime(Timestamp startTime, Timestamp endTime);
}
