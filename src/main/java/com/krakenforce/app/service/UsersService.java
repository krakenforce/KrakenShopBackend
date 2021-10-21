package com.krakenforce.app.service;


import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void updatePassword(Users user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setHashPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        usersRepository.save(user);
    }
	
	
}
