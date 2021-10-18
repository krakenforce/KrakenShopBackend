package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductComment;


@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer>,
PagingAndSortingRepository<ProductComment, Integer>{

}
