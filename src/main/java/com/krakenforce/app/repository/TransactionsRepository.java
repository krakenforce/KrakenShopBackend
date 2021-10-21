package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Transactions;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>,
PagingAndSortingRepository<Transactions, Integer>{

}
