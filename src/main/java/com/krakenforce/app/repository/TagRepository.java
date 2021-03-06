package com.krakenforce.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>,
PagingAndSortingRepository<Tag, Integer>{

	@Query(value = "SELECT * FROM tag WHERE name LIKE %?1% ", nativeQuery = true)
	Page<Tag> findTagByName(String name, Pageable pageable); 
}
