package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer>,
PagingAndSortingRepository<Payments, Integer>{

}
