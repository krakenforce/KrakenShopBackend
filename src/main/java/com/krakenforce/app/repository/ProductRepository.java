package com.krakenforce.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,
PagingAndSortingRepository<Product, Integer>{

	@Query(value = "SELECT * FROM product", nativeQuery = true)
	Page<Product> GetAllProduct(Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE sale_price <= ?1", nativeQuery = true)
	Page<Product> GetProductBelowPrice(float price, Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE sale_price >= ?1 AND sale_price <= ?2 ", nativeQuery = true)
	Page<Product> GetProductPriceRange(float startPrice, float endPrice, Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE name LIKE %?1%", nativeQuery = true)
	Page<Product> getProductByName(String keyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM product WHERE service_pack_id = ?1", nativeQuery = true)
	Page<Product> getProductByProductServicePack(int servicePackId, Pageable pageable);
	
	@Query(value = "SELECT p FROM Product p Join p.tags t WHERE t.name LIKE %?1%" )
	Page<Product> getProductByTag(int tagId, Pageable pageable);
}
