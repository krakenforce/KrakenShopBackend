package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>,
PagingAndSortingRepository<CartItem, Integer>{

}
