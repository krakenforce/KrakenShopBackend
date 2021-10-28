package com.krakenforce.app.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer>,
PagingAndSortingRepository<ProductReview, Integer>{

	@Query(value = "SELECT * FROM product_review WHERE user_id = ?1", nativeQuery = true)
	Page<ProductReview> findByUser(int userId, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_review WHERE product_id = ?1", nativeQuery = true)
	Page<ProductReview> findByProduct(int productId, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_review WHERE created_at BETWEEN ?1 AND ?2", nativeQuery = true)
	Page<ProductReview> findByTime(Instant startTime, Instant endTime, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_review WHERE user_id = ?1 AND created_at BETWEEN ?2 AND ?3", nativeQuery = true)
	Page<ProductReview> findByUserAndTime(int userId, Instant startTime, Instant endTime, Pageable pageable);
	
	
}
