package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.krakenforce.app.model.SalePromote;

public interface SalePromoteRepository extends JpaRepository<SalePromote, Integer>,
PagingAndSortingRepository<SalePromote, Integer>{

}
