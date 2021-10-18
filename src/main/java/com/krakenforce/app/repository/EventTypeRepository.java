package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.EventType;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Integer>,
PagingAndSortingRepository<EventType, Integer>{

}
