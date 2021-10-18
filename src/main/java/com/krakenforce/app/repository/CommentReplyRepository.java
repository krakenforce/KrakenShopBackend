package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.krakenforce.app.model.CommentReply;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Integer>,
PagingAndSortingRepository<CommentReply, Integer>{

}
