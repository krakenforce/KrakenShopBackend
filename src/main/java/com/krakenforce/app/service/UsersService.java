package com.krakenforce.app.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.exception.UsersNotFoundException;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.repository.UsersRepository;

@Service
@Transactional
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Users addUser(Users user) {
		return usersRepository.save(user);
	}
	
	public void updateResetPasswordToken(String token, String email) {
		Users user = usersRepository.FindByEmail(email);
		if(user != null) {
			user.setResetPasswordToken(token);
			usersRepository.save(user);
		}else {
			throw new UsersNotFoundException("Could not find any user with the email " + email);
		}
	}
	
	public Users getByResetPasswordToken(String token) {
		return usersRepository.FindByResetPasswordToken(token);
	}
	
	public Users getById(int userId) {
		return usersRepository.findById(userId).orElse(null);
	}
	
	public void updatePassword(Users user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setHashPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        usersRepository.save(user);
    }
	
	/**
	 * use to get all user with pagination
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	public List<Users> getAllUser(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findAll(paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Users>();
		}
	}
	
	/**
	 * use to get user list by username with pagination
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Users>
	 */
	public List<Users> getUserByUsername(String username, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findUserByUsername(username, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Users>();
		}
	}
	
	/**
	 * use to get user list by vip class id with pagination
	 * @param vipClassId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<User>
	 */
	public List<Users> getUserByVipClass(int vipClassId, Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findUserByVipClass(vipClassId, paging);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Users>();
		}
	}
	
	
	
	
}
