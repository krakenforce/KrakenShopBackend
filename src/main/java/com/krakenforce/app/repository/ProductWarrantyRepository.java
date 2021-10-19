package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductWarranty;


@Repository
public interface ProductWarrantyRepository extends JpaRepository<ProductWarranty, Integer>,
PagingAndSortingRepository<ProductWarranty, Integer>{

}