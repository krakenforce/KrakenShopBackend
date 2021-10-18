package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
PagingAndSortingRepository<Category, Integer>{

}
