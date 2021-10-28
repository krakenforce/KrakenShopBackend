package com.krakenforce.app.repository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductComment;


@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer>,
PagingAndSortingRepository<ProductComment, Integer>{

	@Query(value = "SELECT * FROM product_comment WHERE product_id = ?1", nativeQuery = true)
	Page<ProductComment> findCommentByProduct(int productId, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_comment WHERE user_id = ?1", nativeQuery = true)
	Page<ProductComment> findCommentByUser(int userId, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_comment WHERE comment_time BETWEEN ?1 AND endTime = ?2", nativeQuery = true)
	Page<ProductComment> findCommentByTime(Instant startTime, Instant endTime, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_comment WHERE user_id = ?1 AND comment_time BETWEEN ?2 AND endTime = ?3", nativeQuery = true)
	Page<ProductComment> findCommentByTimeAndUser(int userId, Instant startTime, Instant endTime, Pageable pageable);
}
