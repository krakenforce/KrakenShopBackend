package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductGameCode;


@Repository
public interface ProductGameCodeRepository extends JpaRepository<ProductGameCode, Integer>,
PagingAndSortingRepository<ProductGameCode, Integer>{

}
