package com.krakenforce.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.krakenforce.app.model.SalePromote;

public interface SalePromoteRepository extends JpaRepository<SalePromote, Integer>,
PagingAndSortingRepository<SalePromote, Integer>{
	
	@Query(value = "SELECT * FROM sale_promote WHERE title LIKE %?1%", nativeQuery = true)
	Page<SalePromote> findByKeyword(String keyword, Pageable pageable);

}
