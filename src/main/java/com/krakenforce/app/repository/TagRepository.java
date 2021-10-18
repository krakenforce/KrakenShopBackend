package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>,
PagingAndSortingRepository<Tag, Integer>{

}
