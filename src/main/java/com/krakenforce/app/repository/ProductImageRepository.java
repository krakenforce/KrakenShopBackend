package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductImage;


@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>,
PagingAndSortingRepository<ProductImage, Integer>{

}
