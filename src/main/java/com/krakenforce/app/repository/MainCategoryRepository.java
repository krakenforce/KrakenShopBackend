package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.MainCategory;


@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer>,
PagingAndSortingRepository<MainCategory, Integer>{

}
