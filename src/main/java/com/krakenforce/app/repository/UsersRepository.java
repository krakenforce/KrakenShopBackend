package com.krakenforce.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>,
PagingAndSortingRepository<Users, Integer>{

	/**
	 * Find user by user name
	 * @param username
	 * @return Users
	 */
	Optional<Users> findByUsername(String username);
	
	
	/**
	 * Check exists an user by username
	 * @param username
	 * @return Boolean
	 */
	Boolean existsByUsername(String username);
	
	
	/**
	 * Check exists an user by email
	 * @param email
	 * @return Boolean
	 */
	Boolean existsByEmail(String email);
	
	/**
	 * find user by reset password token
	 * @param token
	 * @return
	 */
	@Query("SELECT u FROM Users u WHERE u.resetPasswordToken = ?1")
	Optional<Users> FindByResetPasswordToken(String resetPasswordToken);
	
	/**
	 * find user by email;
	 * @param email
	 * @return
	 */
	@Query("SELECT u FROM Users u WHERE u.email = ?1")
	Users FindByEmail(String email);
}
