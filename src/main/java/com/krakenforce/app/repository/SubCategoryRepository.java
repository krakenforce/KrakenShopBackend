package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.SubCategory;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>,
PagingAndSortingRepository<SubCategory, Integer>{

}
