package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ShoppingCart;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>,
PagingAndSortingRepository<ShoppingCart, Integer>{
	
	@Query(value = "SELECT * FROM shopping_cart WHERE user_id = ?1", nativeQuery = true)
	ShoppingCart findByUserId(int userId);
}
