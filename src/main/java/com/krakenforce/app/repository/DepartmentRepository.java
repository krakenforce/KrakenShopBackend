package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>,
PagingAndSortingRepository<Department, Integer>{

}
