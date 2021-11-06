package com.krakenforce.app.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Transactions;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>,
PagingAndSortingRepository<Transactions, Integer>{
	
	@Query( value = "SELECT * FROM transactions WHERE payment_id = ?1 ", nativeQuery = true)
	List<Transactions> findByPaymentId(int paymentId);
	
	@Query( value = "SELECT * FROM transactions WHERE description LIKE %?1% ", nativeQuery = true)
	Page<Transactions> findByKeyword(String keyword, Pageable pageable);
	
	@Query( value = "SELECT * FROM transactions WHERE created_at BETWEEN ?1 AND ?2 ", nativeQuery = true)
	Page<Transactions> findByTime(Timestamp startTime, Timestamp endTime, Pageable pageable );
}
