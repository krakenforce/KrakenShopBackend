package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.krakenforce.app.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>,
PagingAndSortingRepository<CartItem, Integer>{

}
