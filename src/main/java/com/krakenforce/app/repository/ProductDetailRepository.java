package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductDetail;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer>,
PagingAndSortingRepository<ProductDetail, Integer>{

}
