package com.krakenforce.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductServicePack;


@Repository
public interface ProductServicePackRepository extends JpaRepository<ProductServicePack, Integer>,
PagingAndSortingRepository<ProductServicePack, Integer>{
	
	@Query(value = "SELECT * FROM product_service_pack WHERE name LIKE %?1%", nativeQuery = true)
	Page<ProductServicePack> findByName(String keyword, Pageable pageable);

}
