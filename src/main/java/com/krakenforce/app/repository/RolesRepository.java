package com.krakenforce.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.enums.ERole;
import com.krakenforce.app.model.Roles;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>,
PagingAndSortingRepository<Roles, Integer>{
	
	/**
	 * Find role by name
	 * @param name
	 * @return Roles
	 */
	Optional<Roles> findByName(ERole name);
}
