package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.SupportCategory;


@Repository
public interface SupportCategoryRepository extends JpaRepository<SupportCategory, Integer>,
PagingAndSortingRepository<SupportCategory, Integer>{

}
