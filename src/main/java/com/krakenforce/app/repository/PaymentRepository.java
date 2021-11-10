package com.krakenforce.app.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Payments;

@Repository
public interface PaymentRepository
		extends JpaRepository<Payments, Integer>, PagingAndSortingRepository<Payments, Integer> {

	@Query(value = "SELECT * FROM payment WHERE provider = ?1", nativeQuery = true)
	Page<Payments> findByProvider(String provider, Pageable pageable);

	@Query(value = "SELECT * FROM payment WHERE created_at BETWEEN ?1 AND ?2", nativeQuery = true)
	Page<Payments> findByTime(Timestamp startTime, Timestamp endTime, Pageable pageable);

	@Query(value = "select p.* from payment as p join wallet as w on p.wallet_id = w.id where w.id = ?1", nativeQuery = true)
	Page<Payments> findByWalletId(int walletId, Pageable pageable);
}
