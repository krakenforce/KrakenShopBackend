package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>,
PagingAndSortingRepository<CartItem, Integer>{

	@Query(value = "SELECT * FROM cart_item WHERE shopping_cart_id = ?1 AND product_id = ?2", nativeQuery = true)
	public CartItem findByProductAndCart(int cartId, int productId);
}
