package com.krakenforce.app.repository;

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
}
