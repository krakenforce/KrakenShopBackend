package com.krakenforce.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>,
PagingAndSortingRepository<Category, Integer>{

	@Query(value = "SELECT * FROM category WHERE name LIKE %?1% ", nativeQuery = true)
	Page<Category> findCategoryByName(String name, Pageable pageable); 
}
