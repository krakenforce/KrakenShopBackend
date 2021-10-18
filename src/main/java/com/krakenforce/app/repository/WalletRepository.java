package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>,
PagingAndSortingRepository<Wallet, Integer>{

}
