package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Support;


@Repository
public interface SupportRepository extends JpaRepository<Support, Integer>,
PagingAndSortingRepository<Support, Integer>{

}
