package com.krakenforce.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.dtos.UsersDtos;
import com.krakenforce.app.enums.ERole;
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
		if (user != null) {
			user.setResetPasswordToken(token);
			usersRepository.save(user);
		} else {
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
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	public Map<String, Object> getAllUser(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findAll(paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Users> userList = pageResult.getContent();
			List<UsersDtos> dtoList = convertListToDto(userList);
			
			response.put("users", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;

		} else {
			return new HashMap<String, Object>();
		}
	}

	/**
	 * use to get user list by username with pagination
	 * 
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<Users>
	 */
	public Map<String, Object> getUserByUsername(String username, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findUserByUsername(username, paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Users> userList = pageResult.getContent();
			List<UsersDtos> dtoList = convertListToDto(userList);
			
			response.put("users", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		} else {
			return new HashMap<String, Object>();
		}
	}
	
	public Map<String, Object> getUserByRole(ERole roleName, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findUserByRole(roleName, paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Users> userList = pageResult.getContent();
			List<UsersDtos> dtoList = convertListToDto(userList);
			
			response.put("users", dtoList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		} else {
			return new HashMap<String, Object>();
		}
	}

	/**
	 * use to get user list by vip class id with pagination
	 * 
	 * @param vipClassId
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return List<User>
	 */
	public Map<String, Object> getUserByVipClass(int vipClassId, Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Users> pageResult = usersRepository.findUserByVipClass(vipClassId, paging);
		if (pageResult.hasContent()) {
			Map<String, Object> response = new HashMap<String, Object>();
			List<Users> users = pageResult.getContent();
			List<UsersDtos> dtosList = convertListToDto(users);
			
			response.put("users", dtosList);
			response.put("currentPage", pageResult.getNumber());
			response.put("totalItems", pageResult.getTotalElements());
			response.put("totalPages", pageResult.getTotalPages());
			return response;
		} else {
			return new HashMap<String, Object>();
		}
	}
	
	
	public List<UsersDtos> convertListToDto(List<Users> userList){
		List<UsersDtos> dtoList = new ArrayList<UsersDtos>();
		for(Users user : userList) {
			UsersDtos dto = new UsersDtos();
			dto.setUserId(user.getUserId());
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			dto.setPhone(user.getPhone());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setIdentityNumber(user.getIdentityNumber());
			dto.setGender(user.getGender());
			dto.setAddress(user.getAddress());
			dto.setJob(user.getJob());
			dto.setMarriageStatus(user.isMarriageStatus());
			dto.setWalletId(user.getWallet().getId());
			dto.setWalletBalance(user.getWallet().getBalance());
			
//			if(user.getFavoriteProducts() != null) {
//				dto.setFavoriteProducts(user.getFavoriteProducts());
//			}
			
			dto.setAvatarImageUrl(user.getAvatarImageUrl());
			dto.setRoleSet(user.getRoleSet());;
			dtoList.add(dto);
		}
		return dtoList;
		
	}

}
